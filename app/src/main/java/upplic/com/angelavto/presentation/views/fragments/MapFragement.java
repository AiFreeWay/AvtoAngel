package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.view_controllers.FmtMapCtrl;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;

public class MapFragement extends BaseFragment<FmtMapCtrl> {

    @BindView(R.id.fmt_map_mv_map)
    MapView mMvMap;
    @BindView(R.id.fmt_map_btn_record)
    Button mBtnRecord;
    @BindView(R.id.fmt_map_btn_log)
    Button mBtnLog;

    private GoogleMap mMap;
    private AvtoFragment mParentFragment;

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
        setHasOptionsMenu(true);
        mParentFragment = (AvtoFragment) getParentFragment();
        mViewController = new FmtMapCtrl(this);
        mMvMap.onCreate(savedInstanceState);
        mMvMap.getMapAsync(this::startMap);
        mBtnRecord.setOnClickListener(v -> mViewController.changeRecord());
        mBtnLog.setOnClickListener(v -> mViewController.openRecordActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.avto_menu_edit)
            mViewController.openEditAvtoActivity();
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.avto_menu, menu);
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewController.stop();
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

    public void notifyFragment() {
        if (getCar() != null)
            initRecordButton();
    }

    public GoogleMap getMap() {
        return mMap;
    }

    public Car getCar() {
        return mParentFragment.getCar();
    }

    public void initRecordButton() {
        if (getCar().isRecord()) {
            mBtnRecord.setTextColor(ContextCompat.getColor(getContext(), R.color.marron));
            mBtnRecord.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_stop, 0, 0, 0);
        } else {
            mBtnRecord.setTextColor(ContextCompat.getColor(getContext(), R.color.grideperlevy));
            mBtnRecord.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_camera, 0, 0, 0);
        }
    }

    private void startMap(GoogleMap map) {
        mMap = map;
        mViewController.start();
    }
}
