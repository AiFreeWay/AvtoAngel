package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.rey.material.widget.ProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.presentation.adapters.MultyListViewAdapter;
import upplic.com.angelavto.presentation.adapters.view_binders.BeaconShopBinder;
import upplic.com.angelavto.presentation.view_controllers.FmtBeaconsShopCtrl;
import upplic.com.angelavto.presentation.view_controllers.ViewController;
import upplic.com.angelavto.presentation.views.activities.MainActivity;


public class BeaconsShopFragment extends BaseFragment<FmtBeaconsShopCtrl> {

    @BindView(R.id.fmt_beacons_shop_lv_beacons)
    ListView mLvBeacons;
    @BindView(R.id.fmt_beacons_shop_tv_error)
    TextView mTvError;
    @BindView(R.id.fmt_beacons_shop_pv_progress)
    ProgressView mPvProgress;

    private MultyListViewAdapter<Beacon> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_beacons_shop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewController = new FmtBeaconsShopCtrl(this);
        mAdapter = new MultyListViewAdapter<Beacon>(new BeaconShopBinder(mViewController));
        mLvBeacons.setAdapter(mAdapter);
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getBaseActivity().getSupportActionBar().setTitle(R.string.buy_beacon);
            ((MainActivity) getBaseActivity()).getToolbar().getMenu().clear();
        } catch (NullPointerException e) {

        }
    }

    public ListView getLvBeacons() {
        return mLvBeacons;
    }

    public void loadData(List<Beacon> beacons) {
        mAdapter.loadData(beacons);
    }

    public void showStartLoad() {
        mTvError.setVisibility(View.INVISIBLE);
        mPvProgress.start();
    }

    public void showSuccesLoad() {
        mPvProgress.stop();
    }

    public void showDeniedLoad() {
        mTvError.setVisibility(View.VISIBLE);
        mPvProgress.stop();
    }
}
