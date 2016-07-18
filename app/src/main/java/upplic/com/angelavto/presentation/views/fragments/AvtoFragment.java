package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoCtrl;

public class AvtoFragment extends BaseFragment<FmtAvtoCtrl> {

    public static final String CAR_ID = "carid";

    private int mCarId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_create_car, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCarId = getArguments().getInt(CAR_ID);
        Log.d("++++", "onActivityCreated: car "+mCarId);
        mViewController = new FmtAvtoCtrl(this);
        mViewController.start();
    }

    public int getCarId() {
        return mCarId;
    }
}
