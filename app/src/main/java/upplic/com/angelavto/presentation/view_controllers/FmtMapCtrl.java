package upplic.com.angelavto.presentation.view_controllers;


import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.fragments.MapFragement;

public class FmtMapCtrl extends ViewController<MapFragement> {

    @Inject
    @Named(ActivityModule.GET_CAR_DETAIL)
    Interactor1<Car, Integer> mGetCarDetal;


    private Subscription mInterval;

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
                            Log.d("++++", "start: "+car.getLat()+" "+car.getLon());
                            LatLng currentPosition = new LatLng(car.getLat(), car.getLon());
                            mRootView.getMap().addMarker(new MarkerOptions().position(currentPosition).title(mRootView.getCar().getTitle()));
                            mRootView.getMap().moveCamera(CameraUpdateFactory.newLatLng(currentPosition));},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtMapCtrl: start error "+e.toString()));
    }

    public void stop() {
        if (mInterval != null)
            mInterval.unsubscribe();
    }
}
