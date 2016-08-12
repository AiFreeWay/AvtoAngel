package upplic.com.angelavto.presentation.views.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    @BindView(R.id.fmt_avto_drive_btn_security)
    Button mBtnStatus;
    @BindView(R.id.fmt_avto_drive_btn_notification)
    Button mBtnNotification;
    @BindView(R.id.fmt_avto_drive_btn_update)
    Button mBtnUpdate;
    @BindView(R.id.fmt_avto_drive_btn_show_on_map)
    Button mBtnShowOnMap;

    private int mColorMarron;
    private int mColorGreen;

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
        mColorMarron = ContextCompat.getColor(getContext(), R.color.marron);
        mColorGreen = ContextCompat.getColor(getContext(), R.color.green_jungle_krayola);
        mFbtnEdit.setOnClickListener(v -> mViewController.openEditAvtoActivity());
    }

    public Car getCar() {
        return mParentFragment.getCar();
    }

    public void notifyFragment() {
        initStatusButton();
        initNotificationButton();
        initUpdateButton();
        initShowOnMapButton();
    }

    private void initStatusButton() {
        mBtnStatus.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_marengo_button));
        if (mParentFragment.getCar().isStatus()) {
            mBtnStatus.setTextColor(mColorGreen);
            mBtnStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_green, 0, 0, 0);
        } else {
            mBtnStatus.setTextColor(mColorMarron);
            mBtnStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_red, 0, 0, 0);
        }
    }
    private void initNotificationButton() {
        mBtnNotification.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_marengo_button));
        if (mParentFragment.getCarOptions().isNotification()) {
            mBtnNotification.setTextColor(mColorGreen);
            mBtnNotification.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications_green, 0, 0, 0);
        } else {
            mBtnNotification.setTextColor(mColorMarron);
            mBtnNotification.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications_red, 0, 0, 0);
        }
    }

    private void initUpdateButton() {
        mBtnUpdate.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_marengo_button));
        mBtnUpdate.setTextColor(Color.WHITE);
        mBtnUpdate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_update_white, 0, 0, 0);
    }
    private void initShowOnMapButton() {
        mBtnShowOnMap.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_green_button));
        mBtnShowOnMap.setTextColor(Color.WHITE);
    }

}
