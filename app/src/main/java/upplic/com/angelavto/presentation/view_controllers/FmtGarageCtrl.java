package upplic.com.angelavto.presentation.view_controllers;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.fragments.GarageFragment;

public class FmtGarageCtrl extends ViewController<GarageFragment> {

    @Inject
    @Named(ActivityModule.GET_CARS)
    Interactor0<List<Car>> mGetCars;

    private LayoutInflater mLayoutInflater;

    public FmtGarageCtrl(GarageFragment view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mGetCars.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars -> mRootView.loadData(cars),
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: start error "+e.toString()));
    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void openEditAvtoActivity(Car data) {

    }
}
