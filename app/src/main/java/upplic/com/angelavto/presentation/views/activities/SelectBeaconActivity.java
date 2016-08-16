package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;

import com.orhanobut.hawk.Hawk;
import com.rey.material.app.Dialog;

import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.AcSelectBeaconCtrl;


public class SelectBeaconActivity extends BaseActivity<AcSelectBeaconCtrl> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_select_beacon);
        mViewController = new AcSelectBeaconCtrl(this);
        mViewController.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            mViewController.popBack();
        return true;
    }

    public int getFragmentsBodyResId() {
        return R.id.ac_select_beacon_fl_fragments;
    }
}
