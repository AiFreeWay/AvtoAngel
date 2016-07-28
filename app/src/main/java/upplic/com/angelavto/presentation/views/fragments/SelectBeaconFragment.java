package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.FmtSelectBeaconController;

public class SelectBeaconFragment extends BaseFragment<FmtSelectBeaconController> {

    @BindView(R.id.v_select_beacon_btn_select_beacon)
    Button mBtnSelectBeacon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_select_beacon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewController = new FmtSelectBeaconController(this);
        mBtnSelectBeacon.setOnClickListener(v -> mViewController.openToBeaconsFragment());
        mViewController.start();
    }
}
