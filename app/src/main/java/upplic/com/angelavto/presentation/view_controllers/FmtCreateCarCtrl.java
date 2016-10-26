package upplic.com.angelavto.presentation.view_controllers;


import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.BeaconsInteractor;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;

public class FmtCreateCarCtrl extends ViewController<CreateCarFragment> {

    @Inject @Named(ActivityModule.CARS)
    CarsInteractor mCarsInteractor;
    @Inject @Named(ActivityModule.BEACONS)
    BeaconsInteractor mBeaconsInteractor;

    public FmtCreateCarCtrl(CreateCarFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mBeaconsInteractor.getBeacons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(products -> {mRootView.loadData(products);
                            mRootView.showSuccesLoad();},
                        e -> { mRootView.showDeniedLoad();
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtCreateCarCtrl: start error "+e.toString());});
    }

    public void addCar(Car car) {
        mCarsInteractor.createCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(upsertCarResult -> {
                            mRootView.showSuccesLoad();
                            if (upsertCarResult.isSuccess()) {
                                mRootView.truncateFields();
                                Toast.makeText(mRootView.getContext(), R.string.create_car_success, Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(mRootView.getContext(), R.string.car_exists, Toast.LENGTH_SHORT).show();},
                        e -> { mRootView.showDeniedLoad();
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtCreateCarCtrl: addCar error "+e.toString());});
    }
}
