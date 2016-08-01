package upplic.com.angelavto.presentation.view_controllers;


import android.content.Context;
import android.content.Intent;
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
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.views.activities.AvtoActivity;
import upplic.com.angelavto.presentation.views.fragments.GarageFragment;

public class FmtGarageCtrl extends ViewController<GarageFragment> {

    @Inject
    @Named(ActivityModule.GET_CARS)
    Interactor0<List<Car>> mGetCars;
    @Inject
    FragmentRouter.RouterBilder mRouterBilder;
    @Inject
    FragmentsFactory mFragmentsFactory;

    private LayoutInflater mLayoutInflater;
    private FragmentRouter mRouter;

    public FmtGarageCtrl(GarageFragment view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mRootView.getFragmentsBodyResId());
    }

    @Override
    public void start() {
        mGetCars.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars -> {
                    if (cars.size() > 0) {
                        mRootView.loadData(cars);
                        mRootView.hideTextViewWarning();
                    } else
                        mRootView.showTextViewWarning();},
                    e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcMainCtrl: start error "+e.toString()));
    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void openAvtoActivity(Car data) {
        Intent intent = new Intent(getRootView().getContext(), AvtoActivity.class);
        intent.putExtra(AvtoActivity.CAR_TAG, data);
        mRootView.startActivity(intent);
    }

    public void openAddCarFragment() {
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.CRAETE_CAR));
    }
}
