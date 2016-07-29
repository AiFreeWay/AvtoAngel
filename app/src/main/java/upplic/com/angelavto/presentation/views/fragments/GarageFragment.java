package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.adapters.MultyListViewAdapter;
import upplic.com.angelavto.presentation.adapters.view_binders.GarageBinder;
import upplic.com.angelavto.presentation.view_controllers.FmtGarageCtrl;

public class GarageFragment extends BaseFragment<FmtGarageCtrl> {

    @BindView(R.id.fmt_garage_lv_cars)
    ListView mLvCars;

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
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        getBaseActivity().getSupportActionBar().setTitle(R.string.garage);
    }

    public void loadData(List<Car> cars) {
        mAdapter.loadData(cars);
    }

    public ListView getLvCars() {
        return mLvCars;
    }
}
