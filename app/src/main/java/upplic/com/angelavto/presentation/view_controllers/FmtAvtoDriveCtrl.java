package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;

public class FmtAvtoDriveCtrl extends ViewController<AvtoDriveFragment> {

    @Inject @Named(ActivityModule.SET_STATUS)
    Interactor1<Status, Status> mSetStatus;
    @Inject @Named(ActivityModule.UPDATE_CAR_OPTIONS)
    Interactor1<CarOptions, CarOptions> mUpdateCarDB;
    @Inject @Named(ActivityModule.OFF_ALARM)
    Interactor0<String> mOffAlarm;

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
        Status status = new Status(car.getId(), car.isStatus(), car.isRecord());
        mSetStatus.execute(status)
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
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: changeState error "+e.toString()));
    }

    public void changeNotification() {
        CarOptions carOptions = mRootView.getCarOptions();
        carOptions.setNotification(!carOptions.isNotification());
        mUpdateCarDB.execute(carOptions)
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
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: changeNotification error "+e.toString()));
    }

    public void offAlarm() {
        mOffAlarm.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            Hawk.remove(AvtoFragment.ALARM_WARNING_TAG);
                            ((AvtoFragment) mRootView.getParentFragment()).setNormalState();
                            mRootView.initAlarmOffButton();},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: offAlarm error "+e.toString()));
    }

    private void notifyMenuItem(Car car) {
        ((MainActivity) mRootView.getParentFragment().getActivity())
                .getViewController()
                .updateMenuItem(car);
    }
}