package upplic.com.angelavto.presentation.view_controllers;

import android.util.Log;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;
import upplic.com.angelavto.presentation.views.fragments.MapFragement;


public class FmtAvtoCtrl extends ViewController<AvtoFragment> {

    private final int AVTO_DRIVE_FRAGMENT = 0;
    private final int MAP_FRAGMENT = 1;

    @Inject @Named(ActivityModule.GET_CAR_DETAIL)
    Interactor1<Car, Integer> mGetCarDetal;
    @Inject
    AvtoViewPagerFactory.Builder mFactoryBuilder;

    private AvtoViewPagerFactory mFactory;

    public FmtAvtoCtrl(AvtoFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mFactory = mFactoryBuilder.build(mRootView.getTlTabs());
    }

    public void putWarningToHawk() {
        Hawk.putObservable(AvtoFragment.ALARM_WARNING_TAG, true)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isPut -> mRootView.setDangerState(),
                        e -> { mRootView.showStopLoad();
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoCtrl: putWarningToHawk error "+e.toString());});
    }

    @Override
    public void start() {
        mRootView.loadData(mFactory.getTabs(), mFactory.getFragments());
        initCarDetail();
    }

    public void initCarDetail() {
       mGetCarDetal.execute(mRootView.getCarOptions().getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(car -> {mRootView.setCatDetail(car);
                            mRootView.showStopLoad();
                            notifyFragments();},
                        e -> { mRootView.showStopLoad();
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoCtrl: initCarDetail error "+e.toString());});
    }

    private void notifyFragments() {
        ((AvtoDriveFragment) mFactory.getFragments().get(AVTO_DRIVE_FRAGMENT)).notifyFragment();
        ((MapFragement) mFactory.getFragments().get(MAP_FRAGMENT)).notifyFragment();
    }
}
