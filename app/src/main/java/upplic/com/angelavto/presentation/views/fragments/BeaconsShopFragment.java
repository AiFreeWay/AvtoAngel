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


public class BeaconsShopFragment extends BaseFragment<FmtBeaconsShopCtrl> {

    @BindView(R.id.fmt_beacons_shop_lv_products)
    ListView mLvProducts;
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
        mLvProducts.setAdapter(mAdapter);
        mLvProducts.addHeaderView(getHeaderView());
        mViewController.start();
    }

    public ListView getLvProducts() {
        return mLvProducts;
    }

    public void loadData(List<Beacon> beacons) {
        mAdapter.loadData(beacons);
    }

    private View getHeaderView() {
        return mViewController.getLayoutInflater().inflate(R.layout.v_beacon_header, mLvProducts, false);
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
