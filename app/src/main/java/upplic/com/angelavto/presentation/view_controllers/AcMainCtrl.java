package upplic.com.angelavto.presentation.view_controllers;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor0;
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
    @Inject @Named(ActivityModule.GET_CARS_DB)
    Interactor0<List<CarOptions>> mGetCarsDB;

    private FragmentRouter mRouter;
    private LayoutInflater mLayoutInflater;
    private List<AppMenuItem> mMenu;

    public AcMainCtrl(MainActivity view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mRootView.getFragmentsBodyResId());
    }

    @Override
    public void start() {
        Hawk.put(LoginActivity.FIRTS_START, true);
        mMenu = mAppMenuFactory.getMenu();
        checkCarsCount();
        mGetCarsDB.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars -> {
                    if (cars.size()>0)
                        startAvtoFragment(getLatestCar(cars));
                    else
                        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.CRAETE_CAR));},
                    e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: start error "+e.toString()));

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

    public void hundleExpandAppMenuClick() {
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.CRAETE_CAR));
        mRootView.driveMenu();
    }


    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void showFragmet(FragmentsFactory.Fragments fragmentIndifinder) {
        mRouter.show(mFragmentsFactory.getFragment(fragmentIndifinder));
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
                .subscribe(cars -> { mRootView.loadData(joinCarsAndMenuItems(mMenu, cars));
                            mRootView.getLvMenu().expandGroup(AppMenuFactory.MenuItems.AVTO.id);},
                        e -> { mRootView.loadData(mMenu);
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: start error "+e.toString());});
    }

    private List<AppMenuItem> joinCarsAndMenuItems(List<AppMenuItem> menues, List<Car> cars) {
        List<AppMenuItem> carsAsMenuItems = CarMapper.mapCars(mRootView, cars);
        menues.get(AppMenuFactory.MenuItems.AVTO.id).setInsertedMenu(carsAsMenuItems);
        return menues;
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
