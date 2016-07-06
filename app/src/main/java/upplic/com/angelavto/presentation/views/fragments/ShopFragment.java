package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.presentation.adapters.MultyListViewAdapter;
import upplic.com.angelavto.presentation.adapters.view_binders.ProductBinder;
import upplic.com.angelavto.presentation.view_controllers.FmtShopCtrl;

public class ShopFragment extends BaseFragment<FmtShopCtrl> {

    @BindView(R.id.fmt_shop_lv_products)
    ListView mLvProducts;
    @BindView(R.id.fmt_shop_tv_error)
    TextView mTvError;
    @BindView(R.id.fmt_shop_pv_progress)
    ProgressView mPvProgress;

    private MultyListViewAdapter<Product> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_shop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewController = new FmtShopCtrl(this);
        mAdapter = new MultyListViewAdapter<Product>(new ProductBinder(mViewController));
        mLvProducts.setAdapter(mAdapter);
        mLvProducts.addHeaderView(getHeaderView());
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        getBaseActivity().getSupportActionBar().setTitle(R.string.buy_beacon);
    }

    public ListView getLvProducts() {
        return mLvProducts;
    }

    public void loadData(List<Product> products) {
        mAdapter.loadData(products);
    }

    private View getHeaderView() {
        return mViewController.getLayoutInflater().inflate(R.layout.v_shop_header, mLvProducts, false);
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
