package upplic.com.angelavto.presentation.view_controllers;


import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

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
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.fragments.MapFragement;

public class FmtMapCtrl extends ViewController<MapFragement> {

    @Inject
    @Named(ActivityModule.GET_CAR_DETAIL)
    Interactor1<Car, Integer> mGetCarDetal;

    private Polyline mRoute;
    private Subscription mInterval;
    private Marker mMarker;

    public FmtMapCtrl(MapFragement view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mInterval = Observable.interval(1, TimeUnit.SECONDS)
                .flatMap(aLong -> mGetCarDetal.execute(mRootView.getCar().getId()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                            if (!(car.getLat() == 0 || car.getLon() == 0)) {
                                drawRoute(car);}},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtMapCtrl: start error "+e.toString()));
    }

    public void stop() {
        if (mInterval != null)
            mInterval.unsubscribe();
    }

    private void drawRoute(Car car) {
        if (mRoute == null)
            createRote(car);
        else
            update(car);
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
}
