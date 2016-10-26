package upplic.com.angelavto.presentation.view_controllers;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;
import upplic.com.angelavto.presentation.views.fragments.MapFragement;


public class FmtAvtoCtrl extends ViewController<AvtoFragment> {

    private final int AVTO_DRIVE_FRAGMENT = 0;
    private final int MAP_FRAGMENT = 1;

    @Inject @Named(ActivityModule.CARS)
    CarsInteractor mCarsInteractor;
    @Inject
    AvtoViewPagerFactory.Builder mFactoryBuilder;

    private AvtoViewPagerFactory mFactory;

    public FmtAvtoCtrl(AvtoFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mFactory = mFactoryBuilder.build(mRootView.getTlTabs());
    }

    @Override
    public void start() {
        mRootView.loadData(mFactory.getTabs(), mFactory.getFragments());
        initCarDetail();
    }

    public void initCarDetail() {
        mCarsInteractor.getCarDetail(mRootView.getCarOptions().getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(car -> {mRootView.setCatDetail(car);
                            mRootView.showStopLoad();
                            notifyFragments();},
                        e -> { mRootView.showStopLoad();
                            Logger.logError(e);});
    }

    private void notifyFragments() {
        ((AvtoDriveFragment) mFactory.getFragments().get(AVTO_DRIVE_FRAGMENT)).notifyFragment();
        ((MapFragement) mFactory.getFragments().get(MAP_FRAGMENT)).notifyFragment();
    }
}
