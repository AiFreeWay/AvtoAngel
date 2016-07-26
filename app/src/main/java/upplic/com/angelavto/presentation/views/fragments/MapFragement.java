package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.view_controllers.FmtMapCtrl;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

public class MapFragement extends SupportMapFragment {

    private FmtMapCtrl mViewController;

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
