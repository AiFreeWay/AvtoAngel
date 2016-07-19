package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.view_controllers.FmtMapCtrl;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

public class MapFragemnt extends SupportMapFragment {

    private FmtMapCtrl mViewController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_map, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewController = new FmtMapCtrl(this);
        mViewController.start();
    }

    public ActivityComponent getActivityComponent() {
        return ((BaseActivity) getActivity()).getActivityComponent();
    }
}
