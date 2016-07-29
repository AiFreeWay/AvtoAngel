package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoDriveCtrl;
import upplic.com.angelavto.presentation.views.activities.AvtoActivity;

public class AvtoDriveFragment extends BaseFragment<FmtAvtoDriveCtrl> {

    @BindView(R.id.fmt_avto_drive_tv_warning)
    TextView mTvWarning;

    private Car mCar;
    private AvtoActivity mActivty;

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
        mActivty = (AvtoActivity) getBaseActivity();
        mCar = mActivty.getCar();
        if (mCar.getState() == Car.STATE_UNLOCK) {
            mTvWarning.setVisibility(View.VISIBLE);
            mTvWarning.setText("Местоположение "+mCar.getTitle()+" изменилось!");
        } else {
            mTvWarning.setVisibility(View.INVISIBLE);
        }
    }
}
