package upplic.com.angelavto.presentation.view_controllers;


import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;

public class FmtCreateCarCtrl extends ViewController<CreateCarFragment> {

    @Inject @Named(ActivityModule.CREATE_CAR)
    Interactor<Car> mCreateCar;

    public FmtCreateCarCtrl(CreateCarFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }

    public void addCar(Car car) {
        mCreateCar.execute(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtCreateCarCtrl: addCar error "+e.toString()));
    }
}
