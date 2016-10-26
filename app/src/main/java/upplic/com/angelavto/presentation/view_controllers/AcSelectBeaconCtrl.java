package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.domain.interactors.DriveCarInteractor;

import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;


public class AcSelectBeaconCtrl extends ViewController<SelectBeaconActivity> {

    @Inject @Named(ActivityModule.CARS)
    CarsInteractor mCarsInteractor;
    @Inject @Named(ActivityModule.DRIVE_CAR)
    DriveCarInteractor mDriveCarInteractor;
    @Inject @Named(ActivityModule.ALARM)
    AlarmInteractor mAlarmInteractor;

    @Inject
    FragmentRouter.RouterBilder mRouterBilder;
    @Inject
    FragmentsFactory mFragmentsFactory;

    private FragmentRouter mRouter;

    public AcSelectBeaconCtrl(SelectBeaconActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mRootView.getFragmentsBodyResId(), mRootView.getSupportFragmentManager());
    }

    @Override
    public void start() {
        mDriveCarInteractor.deleteAllCarOptions()
                .flatMap(aVoid -> mAlarmInteractor.deleteAllAlarms())
                .flatMap(aVoid -> mCarsInteractor.getCars())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars -> {
                        if (cars.size()>0)
                            startMainActivity();
                        else
                            showStartFragment();},
                        e -> {
                            showStartFragment();
                            Logger.logError(e);});
    }

    public void popBack() {
        if (!mRouter.back())
            mRootView.finish();
    }

    public FragmentRouter getRouter() {
        return  mRouter;
    }

    private void showStartFragment() {
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.SELECT_BEACON));
    }

    private void startMainActivity() {
        Intent intent = new Intent(mRootView, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mRootView.startActivity(intent);
        mRootView.finish();
    }
}
