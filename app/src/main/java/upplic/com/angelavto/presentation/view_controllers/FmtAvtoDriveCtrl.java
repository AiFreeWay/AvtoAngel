package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;

public class FmtAvtoDriveCtrl extends ViewController<AvtoDriveFragment> {

    @Inject @Named(ActivityModule.UPDATE_CAR)
    Interactor1<UpsertCarResult, Car> mUpdateCar;
    @Inject @Named(ActivityModule.UPDATE_CAR_OPTIONS)
    Interactor1<CarOptions, CarOptions> mUpdateCarDB;

    public FmtAvtoDriveCtrl(AvtoDriveFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }

    public void openEditAvtoActivity() {
        Car car = mRootView.getCar();
        if (car != null) {
            Intent intent = new Intent(getRootView().getContext(), EditAvtoActivity.class);
            intent.putExtra(EditAvtoActivity.CAR_TAG, car);
            mRootView.startActivity(intent);
        }
    }

    public void changeState() {
        Car car = mRootView.getCar();
        car.setStatus(!car.isStatus());
        mUpdateCar.execute(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    String message = "Настройки для '"+car.getTitle()+"' изменены.";
                    Toast.makeText(getRootView().getContext(), message, Toast.LENGTH_SHORT).show();})
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: changeState error "+e.toString()));
    }

    public void changeNotification() {
        CarOptions carOptions = mRootView.getCarOptions();
        carOptions.setNotification(!carOptions.isNotification());
        mUpdateCarDB.execute(carOptions)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    String message = "Настройки для '"+carOptions.getTitle()+"' изменены.";
                    Toast.makeText(getRootView().getContext(), message, Toast.LENGTH_SHORT).show();
                    mRootView.initNotificationButton();})
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: changeNotification error "+e.toString()));
    }
}
