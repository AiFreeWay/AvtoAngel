package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.FmtCreateCarCtrl;


public class CreateCarFragment extends BaseFragment<FmtCreateCarCtrl> {

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
        mViewController = new FmtCreateCarCtrl(this);
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        getBaseActivity().getSupportActionBar().setTitle(R.string.add_car);
    }
}
