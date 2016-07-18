package upplic.com.angelavto.presentation.view_controllers;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.FragmentsFactory;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;


public class FmtAvtoCtrl extends ViewController<AvtoFragment> {

    @Inject
    @Named(ActivityModule.GET_CAR_BY_ID)
    Interactor1<Car, Integer> mGetCars;

    public FmtAvtoCtrl(AvtoFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mGetCars.execute(mRootView.getCarId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                    mRootView.getBaseActivity()
                        .getSupportActionBar()
                        .setTitle(car.getTitle());},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtAvtoCtrl: start error "+e.toString()));
    }
}
