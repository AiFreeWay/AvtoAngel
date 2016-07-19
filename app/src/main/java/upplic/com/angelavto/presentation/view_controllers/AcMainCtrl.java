package upplic.com.angelavto.presentation.view_controllers;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.mappers.CarMapper;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.factories.AppMenuFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
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
        mMenu = mAppMenuFactory.getMenu();
        checkCarsCount();
    }

    public void popBack() {
        if (!mRouter.back())
            mRootView.finish();
    }

    public void hundleClick(AppMenuItem data) {
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
                            mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.SHOP));},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: start error "+e.toString()));
    }

    private List<AppMenuItem> joinCarsAndMenuItems(List<AppMenuItem> menues, List<Car> cars) {
        List<AppMenuItem> carsAsMenuItems = CarMapper.mapCars(mRootView, cars);
        menues.get(AppMenuFactory.MenuItems.AVTO.id).setInsertedMenu(carsAsMenuItems);
        return menues;
    }


    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void showFragmet(FragmentsFactory.Fragments fragmentIndifinder) {
        mRouter.show(mFragmentsFactory.getFragment(fragmentIndifinder));
    }
}
