package upplic.com.angelavto.presentation.view_controllers;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
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
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.LocationAdressManager;
import upplic.com.angelavto.presentation.utils.LocationListenerGoogleApiClient;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.activities.RecordsActivity;
import upplic.com.angelavto.presentation.views.fragments.MapFragement;

public class FmtMapCtrl extends ViewController<MapFragement> {

    private static final int ONLY_ADDRESS = 0;
    private static final int AREA = 2;
    private static final int CITY = 1;
    private static final int STREET_HOUSE = 0;

    @Inject
    @Named(ActivityModule.GET_CAR_DETAIL)
    Interactor1<Car, Integer> mGetCarDetal;
    @Inject
    @Named(ActivityModule.SET_STATUS)
    Interactor1<Status, Status> mSetStatus;

    private Polyline mRoute;
    private Subscription mInterval;
    private Marker mMarker;
    private GoogleApiClient mGoogleApiClient;
    private Location mMyCurrentLocation;
    private LocationListenerGoogleApiClient mGetCurrentLocationListener;
    private LocationAdressManager mLocationAdressManager;

    public FmtMapCtrl(MapFragement view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);

        mLocationAdressManager = new LocationAdressManager(mRootView.getContext(), addresses -> {
            Address address = addresses.get(ONLY_ADDRESS);
            String location = address.getAddressLine(AREA) + ", " + address.getAddressLine(CITY) + ", " + address.getAddressLine(STREET_HOUSE);
            mRootView.setLocation(location);});

        mGetCurrentLocationListener = new LocationListenerGoogleApiClient(() -> {
            if (ActivityCompat.checkSelfPermission(mRootView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mRootView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            } else
                mMyCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);});

        if (mGoogleApiClient == null)
            mGoogleApiClient = new GoogleApiClient.Builder(mRootView.getContext())
                    .addConnectionCallbacks(mGetCurrentLocationListener)
                    .addOnConnectionFailedListener(mGetCurrentLocationListener)
                    .addApi(LocationServices.API)
                    .build();
    }

    @Override
    public void start() {
        connectGoogleApiClient();
        mInterval = Observable.interval(3, TimeUnit.SECONDS)
                .flatMap(aLong -> mGetCarDetal.execute(mRootView.getCar().getId()))
                .distinct()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                            if (!(car.getLat() == 0 || car.getLon() == 0)) {
                                drawRoute(car);}},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtMapCtrl: start error " + e.toString()));
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

    public void connectGoogleApiClient() {
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    public void disconnectGoogleApiClient() {
        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
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
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtMapCtrl: changeRecord error " + e.toString()));
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
        connectGoogleApiClient();
        setDistance();
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
        mLocationAdressManager.start(mMarker.getPosition().latitude, mMarker.getPosition().longitude);
    }

    private void setDistance() {
        String distance = null;
        try {
            if (mMyCurrentLocation != null) {
                float[] distanseArray = new float[1];
                Location.distanceBetween(mMyCurrentLocation.getLatitude(), mMyCurrentLocation.getLongitude(), mMarker.getPosition().latitude, mMarker.getPosition().longitude, distanseArray);
                float distanseInMetters = distanseArray[0];
                if (distanseInMetters > 1000) {
                    float distanseInKilometters = distanseInMetters/1000;
                    distance = "Расстояние "+String.format("%.2f", distanseInKilometters)+" км.";
                } else
                    distance = "Расстояние "+((int) distanseInMetters)+" м.";
            }

        } catch (Exception e) {
            Log.d("++++", "setDistance: error "+e.toString());
        }
        mRootView.setDistance(distance);
    }
}
