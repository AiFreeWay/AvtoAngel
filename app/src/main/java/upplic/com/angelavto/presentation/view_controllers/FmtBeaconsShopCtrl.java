package upplic.com.angelavto.presentation.view_controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.BeaconsInteractor;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.fragments.BeaconsShopFragment;


public class FmtBeaconsShopCtrl extends ViewController<BeaconsShopFragment> {

    @Inject @Named(ActivityModule.BEACONS)
    BeaconsInteractor mBeaconsInteractor;

    private LayoutInflater mLayoutInflater;

    public FmtBeaconsShopCtrl(BeaconsShopFragment view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mBeaconsInteractor.getBeacons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(products -> {mRootView.loadData(products);
                            mRootView.showSuccesLoad();},
                        e -> {
                            Logger.logError(e);
                            mRootView.showDeniedLoad();});
    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void hundleProductItemClick(Beacon data) {

    }
}
