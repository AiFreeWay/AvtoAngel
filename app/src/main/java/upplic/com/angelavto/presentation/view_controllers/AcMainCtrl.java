package upplic.com.angelavto.presentation.view_controllers;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Alarm;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.mappers.CarMapper;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.factories.AppMenuFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.wrappers.AbstractHundleMemento;
import upplic.com.angelavto.presentation.wrappers.ActionHundleMemento;
import upplic.com.angelavto.presentation.wrappers.ActivityHandleMemento;
import upplic.com.angelavto.presentation.wrappers.FragmentHandleMemento;


public class AcMainCtrl extends ViewController<MainActivity> {

    @Inject
    FragmentRouter.RouterBilder mRouterBilder;
    @Inject
    FragmentsFactory mFragmentsFactory;
    @Inject
    AppMenuFactory mAppMenuFactory;
    @Inject @Named(ActivityModule.GET_CARS)
    Interactor0<List<Car>> mGetCars;
    @Inject @Named(ActivityModule.GET_CAR_OPTIONS)
    Interactor0<List<CarOptions>> mGetCarsOptions;
    @Inject @Named(ActivityModule.CHECK_KEY)
    Interactor0<Boolean> mCheckKey;
    @Inject @Named(ActivityModule.CHECK_ALARM)
    Interactor0<List<Alarm>> mCheckAlarm;

    private FragmentRouter mRouter;
    private LayoutInflater mLayoutInflater;
    private List<AppMenuItem> mMenu;
    private Subscription mInterval;

    public AcMainCtrl(MainActivity view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mRootView.getFragmentsBodyResId(), mRootView.getSupportFragmentManager());
    }

    @Override
    public void start() {
        mCheckKey.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (!result)
                                mRootView.showDialog();},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcSelectBeaconCtrl: start error "+e.toString()));
        Hawk.put(LoginActivity.FIRTS_START, true);
        mMenu = mAppMenuFactory.getMenu();
        checkCarsCount();
    }

    public void popBack() {
        if (mRootView.isDrawerOpen())
            mRootView.driveMenu();
        if (!mRouter.back())
            mRootView.finish();
    }

    public void hundleAppMenuClick(AppMenuItem data) {
        AbstractHundleMemento hundleMemento = data.getAppMenuHundler();
        if (hundleMemento != null && hundleMemento.mHundlerType == AbstractHundleMemento.MenuHandlers.FRAGMENT) {
            FragmentHandleMemento fragmentHandleMemento = (FragmentHandleMemento) hundleMemento;
            hundleFragment(fragmentHandleMemento);
        } else if (hundleMemento != null && hundleMemento.mHundlerType == AbstractHundleMemento.MenuHandlers.ACTION) {
            ActionHundleMemento actionHandleMemento = (ActionHundleMemento) hundleMemento;
            hundleAction(actionHandleMemento);
        } else if (hundleMemento != null && hundleMemento.mHundlerType == AbstractHundleMemento.MenuHandlers.ACTIVITY) {
            ActivityHandleMemento activityHandleMemento = (ActivityHandleMemento) hundleMemento;
            hundleActivity(activityHandleMemento);
        }
        mRootView.driveMenu();
    }


    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void showFragmet(FragmentsFactory.Fragments fragmentIndifinder) {
        mRouter.show(mFragmentsFactory.getFragment(fragmentIndifinder));
    }

    public void updateMenuItem(Car car) {
        List<AppMenuItem> menuItems = mRootView.getData();
        for (AppMenuItem item : menuItems) {
            if (item.getExpannableList().size()>0) {
                findAndChangeMenuItem(car, item);
                mRootView.getAdapter().notifyDataSetChanged();
                break;
            }
        }
    }

    public void stop() {
        if (mInterval != null)
            mInterval.unsubscribe();
    }

    private void findAndChangeMenuItem(Car car, AppMenuItem menuItem) {
        int drawable;
        if (car.isStatus())
            drawable = R.drawable.ic_lock_green;
        else
            drawable = R.drawable.ic_lock_red;
        for (AppMenuItem item : menuItem.getExpannableList()) {
            if (item.getTitle().equals(car.getTitle())) {
                item.setDrawable(drawable);
                break;
            }
        }
    }

    private void hundleFragment(FragmentHandleMemento fragmentHandleMemento) {
        BaseFragment fragment = mFragmentsFactory.getFragment(fragmentHandleMemento.getHundleObject());
        if (fragmentHandleMemento.getFragmentArgs() != null)
            fragment.setArguments(fragmentHandleMemento.getFragmentArgs());
        mRouter.show(fragment);
    }

    private void hundleAction(ActionHundleMemento actionHandleMemento) {
        Action0 action = actionHandleMemento.getHundleObject();
        if (action != null)
            action.call();
    }

    private void hundleActivity(ActivityHandleMemento activityHandleMemento) {
        Intent intent = activityHandleMemento.getHundleObject();
        if (intent != null)
            mRootView.startActivity(intent);
    }


    private void checkCarsCount() {
        mGetCars.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showStartFragment,
                        e -> {showStartFragment(null);
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: checkCarsCount error "+e.toString());});
    }

    private List<AppMenuItem> joinCarsAndMenuItems(List<AppMenuItem> menues, List<Car> cars, List<CarOptions> carOptionses) {
        List<AppMenuItem> carsAsMenuItems = CarMapper.mapCars(mRootView, cars, carOptionses);
        menues.get(AppMenuFactory.MenuItems.AVTO.id).setInsertedMenu(carsAsMenuItems);
        return menues;
    }

    private void showStartFragment(List<Car> cars) {
        mGetCarsOptions.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carOptionses -> {
                            mRootView.loadData(joinCarsAndMenuItems(mMenu, cars, carOptionses));
                            mRootView.getLvMenu().expandGroup(AppMenuFactory.MenuItems.AVTO.id);
                            if (cars.size()>0)
                                startAvtoFragment(getLatestCar(carOptionses));
                            else
                                mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.CRAETE_CAR));
                            startAlarmChecking();},
                        e -> { mRootView.loadData(mMenu);
                            mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.CRAETE_CAR));
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: showStartFragment error "+e.toString());});
    }

    private void startAlarmChecking() {
        mInterval = Observable.interval(3, TimeUnit.SECONDS)
                .flatMap(aLong -> mCheckAlarm.execute())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkAlarm,
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: startAlarmChecking error "+e.toString()));
    }

    private void checkAlarm(List<Alarm> alarms) {
    }

    private CarOptions getLatestCar(List<CarOptions> cars) {
        CarOptions latestCar = new CarOptions();
        for (CarOptions car : cars)
            if (car.getEditDate()>latestCar.getEditDate())
                latestCar = car;
        return latestCar;
    }

    private void startAvtoFragment(CarOptions car) {
        Fragment fragment = mFragmentsFactory.getFragment(FragmentsFactory.Fragments.AVTO);
        fragment.getArguments().putSerializable(AvtoFragment.CAR_OPTIONS_TAG, car);
        mRouter.show(fragment);
    }
}
