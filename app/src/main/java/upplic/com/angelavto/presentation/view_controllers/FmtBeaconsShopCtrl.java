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
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.fragments.BeaconsShopFragment;


public class FmtBeaconsShopCtrl extends ViewController<BeaconsShopFragment> {

    @Inject
    @Named(ActivityModule.GET_BEACONS)
    Interactor0<List<Beacon>> mGetBeacons;

    private LayoutInflater mLayoutInflater;

    public FmtBeaconsShopCtrl(BeaconsShopFragment view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mGetBeacons.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(products -> {mRootView.loadData(products);
                            mRootView.showSuccesLoad();},
                        e -> { Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtBeaconsShopCtrl: start error "+e.toString());
                            mRootView.showDeniedLoad();});
    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void hundleProductItemClick(Beacon data) {

    }
}