package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;
import android.database.Observable;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.activities.
        SelectBeaconActivity;

public class AcSelectBeaconCtrl extends ViewController<SelectBeaconActivity> {

    @Inject @Named(ActivityModule.DELETE_ALL_CAR_OPTIONS)
    Interactor0<Integer> mDeleteAllCarOptions;
    @Inject @Named(ActivityModule.ALARM)
    AlarmInteractor mAlarmInteractor;
    @Inject @Named(ActivityModule.GET_CARS_WITHOUT_SUBJECT)
    Interactor0<List<Car>> mGetCars;
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
        mDeleteAllCarOptions.execute()
                .flatMap(aVoid -> mAlarmInteractor.deleteAllAlarms())
                .flatMap(aVoid -> mGetCars.execute())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars -> {
                        if (cars.size()>0)
                            startMainActivity();
                        else
                            showStartFragment();},
                        e -> {showStartFragment();
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcSelectBeaconCtrl start error: "+e.toString());});
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
