package upplic.com.angelavto.presentation.view_controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;


import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import upplic.com.angelavto.domain.interactors.MapInteractor;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.RecordRouteActivity;
import upplic.com.angelavto.presentation.views.activities.RecordsActivity;

public class AcRecordsCtrl extends ViewController<RecordsActivity> {

    @Inject @Named(ActivityModule.MAP)
    MapInteractor mMapInteractor;

    private LayoutInflater mLayoutInflater;


    public AcRecordsCtrl(RecordsActivity view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mMapInteractor.getRecords(mRootView.getCarId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(records -> {mRootView.loadData(records);
                            if (records.size() == 0)
                                mRootView.showEmptyRecords();
                            mRootView.showSuccesLoad();},
                        e -> { Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcRecordsCtrl: start error "+e.toString());
                            mRootView.showDeniedLoad();});
    }

    public void openRecordReoute(Record record) {
        Intent intent = new Intent(getRootView(), RecordRouteActivity.class);
        intent.putExtra(RecordRouteActivity.RECORD_TAG, record);
        mRootView.startActivity(intent);
    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }
}
