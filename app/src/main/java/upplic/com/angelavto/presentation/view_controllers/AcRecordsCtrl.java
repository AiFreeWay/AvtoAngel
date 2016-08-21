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
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.RecordRouteActivity;
import upplic.com.angelavto.presentation.views.activities.RecordsActivity;

public class AcRecordsCtrl extends ViewController<RecordsActivity> {

    @Inject @Named(ActivityModule.GET_RECORDS)
    Interactor1<List<Record>, Integer> mGetRecords;

    private LayoutInflater mLayoutInflater;


    public AcRecordsCtrl(RecordsActivity view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mGetRecords.execute(mRootView.getCarId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(records -> {mRootView.loadData(records);
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
