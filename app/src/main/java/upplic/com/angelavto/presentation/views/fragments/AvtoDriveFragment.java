package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoDriveCtrl;


public class AvtoDriveFragment extends BaseFragment<FmtAvtoDriveCtrl> {

    @BindView(R.id.fmt_avto_drive_tv_warning)
    TextView mTvWarning;
    @BindView(R.id.fmt_avto_drive_fbtn_edit)
    FloatingActionButton mFbtnEdit;

    private AvtoFragment mParentFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_avto_drive, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewController = new FmtAvtoDriveCtrl(this);
        mParentFragment = (AvtoFragment) getParentFragment();
        mFbtnEdit.setOnClickListener(v -> mViewController.openEditAvtoActivity());
    }

    public Car getCar() {
        return mParentFragment.getCar();
    }
}
