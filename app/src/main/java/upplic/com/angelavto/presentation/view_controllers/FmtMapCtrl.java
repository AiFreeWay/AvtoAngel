package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.activities.RecordsActivity;
import upplic.com.angelavto.presentation.views.fragments.MapFragement;

public class FmtMapCtrl extends ViewController<MapFragement> {

    private static final int ONLY_ADDRESS = 0;
    private static final int AREA = 2;
    private static final int CITY = 1;
    private static final int STREET_HOUSE = 0;

    @Inject @Named(ActivityModule.GET_CAR_DETAIL)
    Interactor1<Car, Integer> mGetCarDetal;
    @Inject @Named(ActivityModule.SET_STATUS)
    Interactor1<Status, Status> mSetStatus;

    private Polyline mRoute;
    private Subscription mInterval;
    private Marker mMarker;
    private Geocoder mGeocoder;

    public FmtMapCtrl(MapFragement view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mGeocoder = new Geocoder(mRootView.getContext());
    }

    @Override
    public void start() {
        mInterval = Observable.interval(3, TimeUnit.SECONDS)
                .flatMap(aLong -> mGetCarDetal.execute(mRootView.getCar().getId()))
                .distinct()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                            if (!(car.getLat() == 0 || car.getLon() == 0)) {
                                drawRoute(car);}},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtMapCtrl: start error "+e.toString()));
    }

    public void restart() {
        if (mRootView.getMap() != null) {
            if (mInterval != null)
                mInterval.unsubscribe();
            start();
        }
    }

    public void stop() {
        if (mInterval != null)
            mInterval.unsubscribe();
    }

    public void changeRecord() {
        Car car = mRootView.getCar();
        car.setRecord(!car.isRecord());
        Status status = new Status(car.getId(), car.isStatus(), car.isRecord());
        mSetStatus.execute(status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    String message;
                    if (car.isRecord())
                        message = "Запись включена";
                    else
                        message = "Запись закончена";
                    Toast.makeText(getRootView().getContext(), message, Toast.LENGTH_SHORT).show();
                    mRootView.initRecordButton();})
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: changeState error "+e.toString()));
    }

    public void openRecordActivity() {
        Car car = getRootView().getCar();
        if (car != null) {
            Intent intent = new Intent(mRootView.getContext(), RecordsActivity.class);
            intent.putExtra(RecordsActivity.CAR_ID_KEY, car.getId());
            mRootView.startActivity(intent);
        }
    }

    public void openEditAvtoActivity() {
        Car car = mRootView.getCar();
        if (car != null) {
            Intent intent = new Intent(getRootView().getContext(), EditAvtoActivity.class);
            intent.putExtra(EditAvtoActivity.CAR_TAG, car);
            mRootView.startActivity(intent);
        }
    }

    private void drawRoute(Car car) {
        if (mRoute == null)
            createRote(car);
        else
            update(car);
        setLocation();
    }

    private void createRote(Car car) {
        LatLng currentPosition = new LatLng(car.getLat(), car.getLon());
        PolylineOptions route = new PolylineOptions()
                .add(currentPosition);
        route.color(ContextCompat.getColor(getRootView().getContext(), R.color.marron));
        route.width(3);
        mRoute = mRootView.getMap().addPolyline(route);
        mRootView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 14));
        mMarker = mRootView.getMap().addMarker(new MarkerOptions()
                .title(car.getTitle())
                .position(currentPosition)
                .draggable(false));
    }

    private void update(Car car) {
        LatLng currentPosition = new LatLng(car.getLat(), car.getLon());
        List<LatLng> points = mRoute.getPoints();
        points.add(currentPosition);
        mRoute.setPoints(points);
        mMarker.setPosition(currentPosition);
    }

    private void setLocation() {
        String location = null;
        try {
            List<Address> addresses = mGeocoder.getFromLocation(mMarker.getPosition().latitude, mMarker.getPosition().longitude, 3);
            Address address = addresses.get(ONLY_ADDRESS);
            location = address.getAddressLine(AREA)+", "+address.getAddressLine(CITY)+", "+address.getAddressLine(STREET_HOUSE);
        } catch (IOException e) {

        }
        mRootView.setLocation(location);
    }
}
