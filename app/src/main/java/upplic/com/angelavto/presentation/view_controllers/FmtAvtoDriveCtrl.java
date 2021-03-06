package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.DriveCarInteractor;
import upplic.com.angelavto.domain.models.Alarm;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;

public class FmtAvtoDriveCtrl extends ViewController<AvtoDriveFragment> {

    @Inject @Named(ActivityModule.DRIVE_CAR)
    DriveCarInteractor mDriveCarInteractor;
    @Inject @Named(ActivityModule.ALARM)
    AlarmInteractor mAlarmInteractor;

    public FmtAvtoDriveCtrl(AvtoDriveFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        checkAlarm();
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
        Status status = new Status(car.getId(), car.isStatus(), car.isRecord());
        mDriveCarInteractor.setStatus(status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    String message = null;
                    if (car.isStatus())
                        message = "Защита для '"+car.getTitle()+"' включена.";
                    else
                        message = "Защита для '"+car.getTitle()+"' отключена.";
                    Toast.makeText(getRootView().getContext(), message, Toast.LENGTH_SHORT).show();
                    notifyMenuItem(car);
                    mRootView.initStatusButton();})
                .subscribe(aVoid -> {},
                        Logger::logError);
    }

    public void changeNotification() {
        CarOptions carOptions = mRootView.getCarOptions();
        carOptions.setNotification(!carOptions.isNotification());
        mDriveCarInteractor.updateCarOptions(carOptions)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    String message = null;
                    if (carOptions.isNotification())
                        message = "Оповещения об охране для '"+carOptions.getTitle()+"' включены.";
                    else
                        message = "Оповещения об охране для для '"+carOptions.getTitle()+"' отключены.";
                    Toast.makeText(getRootView().getContext(), message, Toast.LENGTH_SHORT).show();
                    mRootView.initNotificationButton();})
                .subscribe(aVoid -> {},
                        Logger::logError);
    }

    public void offAlarm() {
        mAlarmInteractor.offAlarm()
                .flatMap(result -> mAlarmInteractor.deleteAlarmById(mRootView.getCarOptions().getId()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            ((AvtoFragment) mRootView.getParentFragment()).setNormalState();
                            mRootView.hideWarning();
                            mRootView.disableAlarmOffButton();},
                        Logger::logError);
    }

    public void checkAlarm() {
        mAlarmInteractor.getAlarmsFromDB()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(alarms -> {
                            if (alarms.size()>0) {
                                ((AvtoFragment) mRootView.getParentFragment()).setDangerState();
                                for (Alarm alarm : alarms) {
                                    if (alarm.getCarId() == mRootView.getCarOptions().getId()) {
                                        mRootView.showWarning(alarm.getTitle());
                                        mRootView.initAlarmOffButton();
                                        break;}}}},
                        Logger::logError);
    }

    private void notifyMenuItem(Car car) {
        ((MainActivity) mRootView.getParentFragment().getActivity())
                .getViewController()
                .updateMenuItem(car);
    }
}