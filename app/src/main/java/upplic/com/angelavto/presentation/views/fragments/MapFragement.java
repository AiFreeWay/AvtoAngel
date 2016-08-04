package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.view_controllers.FmtMapCtrl;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

public class MapFragement extends BaseFragment<FmtMapCtrl> {

    @BindView(R.id.fmt_map_mv_map)
    MapView mMvMap;

    private GoogleMap mMap;

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
        mMvMap.onCreate(savedInstanceState);
        mMvMap.getMapAsync(this::startMap);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMvMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMvMap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMvMap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMvMap.onLowMemory();
    }

    public GoogleMap getMap() {
        return mMap;
    }

    private void startMap(GoogleMap map) {
        mMap = map;
        mViewController.start();
    }
}
