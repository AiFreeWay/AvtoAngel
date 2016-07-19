package upplic.com.angelavto.presentation.view_controllers;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.views.activities.AvtoActivity;


public class AcAvtoCtrl extends ViewController<AvtoActivity> {

    @Inject
    @Named(ActivityModule.GET_CAR_BY_ID)
    Interactor1<Car, Integer> mGetCars;
    @Inject
    AvtoViewPagerFactory.Builder mFactoryBuilder;

    private AvtoViewPagerFactory mFactory;

    public AcAvtoCtrl(AvtoActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mFactory = mFactoryBuilder.build(mRootView.getTlTabs());
        mRootView.loadData(mFactory.getTabs(), mFactory.getFragments());

    }

    @Override
    public void start() {
        mGetCars.execute(mRootView.getCarId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                    mRootView.getSupportActionBar()
                        .setTitle(car.getTitle());},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcAvtoCtrl: start error "+e.toString()));
    }
}
