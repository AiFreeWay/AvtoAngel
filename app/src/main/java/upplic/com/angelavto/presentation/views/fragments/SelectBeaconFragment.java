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
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.view_controllers.FmtSelectBeaconController;
import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;

public class SelectBeaconFragment extends BaseFragment<FmtSelectBeaconController> {

    @BindView(R.id.v_select_beacon_btn_start_app)
    Button mBtnStartApp;
    @BindView(R.id.v_select_beacon_btn_open_shop)
    Button mBtnOpenShop;

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
        mBtnStartApp.setOnClickListener(v -> mViewController.startMainActivity());
        mBtnOpenShop.setOnClickListener(v -> mViewController.showBeaconsShopFragment());
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getBaseActivity().getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }
    }

    public FragmentRouter getRouter() {
        return  ((SelectBeaconActivity) getActivity()).getRouter();
    }
}
