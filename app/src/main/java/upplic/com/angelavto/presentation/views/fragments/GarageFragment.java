package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rey.material.widget.ProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.adapters.MultyListViewAdapter;
import upplic.com.angelavto.presentation.adapters.view_binders.GarageBinder;
import upplic.com.angelavto.presentation.view_controllers.FmtGarageCtrl;
import upplic.com.angelavto.presentation.views.activities.MainActivity;

public class GarageFragment extends BaseFragment<FmtGarageCtrl> {

    @BindView(R.id.fmt_garage_lv_cars)
    ListView mLvCars;
    @BindView(R.id.fmt_garage_btn_add_car)
    Button mBtnAddCar;
    @BindView(R.id.fmt_garage_tv_warning)
    TextView mTvWarning;
    @BindView(R.id.fmt_garage_pv_progress)
    ProgressView mPvProgress;

    private MultyListViewAdapter<Car> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_garage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewController = new FmtGarageCtrl(this);
        mAdapter = new MultyListViewAdapter<Car>(new GarageBinder(mViewController));
        mLvCars.setAdapter(mAdapter);
        mBtnAddCar.setOnClickListener(v -> mViewController.openAddCarFragment());
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getBaseActivity().getSupportActionBar().setTitle(R.string.garage);
        } catch (NullPointerException e) {

        }
    }

    public void loadData(List<Car> cars) {
        mAdapter.loadData(cars);
    }

    public ListView getLvCars() {
        return mLvCars;
    }

    public void showStartLoad() {
        mTvWarning.setVisibility(View.INVISIBLE);
        mPvProgress.start();
    }

    public void showSuccesLoad() {
        mPvProgress.stop();
    }

    public void showDeniedLoad() {
        mTvWarning.setText(R.string.cant_load_data);
        mTvWarning.setVisibility(View.VISIBLE);
        mPvProgress.stop();
    }

    public void showEmptyGarageWarning() {
        mTvWarning.setVisibility(View.VISIBLE);
        mTvWarning.setText(R.string.no_cars_in_garage);
    }

    public void hideEmptyGarageWarning() {
        mTvWarning.setVisibility(View.INVISIBLE);
    }

    public int getFragmentsBodyResId() {
        return ((MainActivity) getBaseActivity()).getFragmentsBodyResId();
    }
}
