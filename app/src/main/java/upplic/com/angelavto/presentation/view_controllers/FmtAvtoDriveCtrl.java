package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;


public class FmtAvtoDriveCtrl extends ViewController<AvtoDriveFragment> {

    @Inject @Named(ActivityModule.SET_STATUS)
    Interactor1<Status, Status> mSetStatus;
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

    public void changeState(boolean isChecked) {
        Car car = mRootView.getCar();
        car.setStatus(isChecked);
        Status status = new Status(car.getId(), car.isStatus(), car.isRecord());
        mSetStatus.execute(status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    notifyMenuItem(car);
                    mRootView.setStatusValue(isChecked);})
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: changeState error "+e.toString()));
    }

    public void changeNotification(boolean isChecked) {
        CarOptions carOptions = mRootView.getCarOptions();
        carOptions.setNotification(isChecked);
        mUpdateCarDB.execute(carOptions)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    mRootView.setNotificationValue(isChecked);})
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoDriveCtrl: changeNotification error "+e.toString()));
    }

    private void notifyMenuItem(Car car) {
        ((MainActivity) mRootView.getParentFragment().getActivity())
                .getViewController()
                .updateMenuItem(car);
    }
}
