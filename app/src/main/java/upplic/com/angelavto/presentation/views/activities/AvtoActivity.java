package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.AcAvtoCtrl;

public class AvtoActivity extends BaseActivity<AcAvtoCtrl> {

    public static final String CAR_ID = "carid";

    private int mCarId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_auto);
        ButterKnife.bind(this);
        mCarId = getIntent().getIntExtra(CAR_ID, -1);
        mViewController = new AcAvtoCtrl(this);
        mViewController.start();
    }

    public int getCarId() {
        return mCarId;
    }

}
