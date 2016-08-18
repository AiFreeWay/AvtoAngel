package upplic.com.angelavto.presentation.view_controllers;


import android.content.Context;
import android.view.LayoutInflater;

import upplic.com.angelavto.presentation.views.activities.RecordsActivity;

public class AcRecordsCtrl extends ViewController<RecordsActivity> {

    private LayoutInflater mLayoutInflater;

    public AcRecordsCtrl(RecordsActivity view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }
}
