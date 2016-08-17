package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;


public class AcEditAvtoCtrl extends ViewController<EditAvtoActivity> {

    @Inject @Named(ActivityModule.UPDATE_CAR)
    Interactor1<UpsertCarResult, Car> mUpdateCar;
    @Inject @Named(ActivityModule.DELETE_CAR)
    Interactor1<DeleteCarResult, Car> mDeleteCar;

    public AcEditAvtoCtrl(EditAvtoActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }

    public void updateCar(Car car) {
        mUpdateCar.execute(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    String message = "Настройки для '"+car.getTitle()+"' изменены.";
                    Toast.makeText(getRootView(), message, Toast.LENGTH_SHORT).show();})
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGarageCtrl: hundleClick error "+e.toString()));
    }

    public void deleteCar(Car car) {
        mDeleteCar.execute(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleteCarResult -> backToMainActivity(),
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGarageCtrl: hundleClick error "+e.toString()));
    }

    private void backToMainActivity() {
        mRootView.finish();
    }
}
