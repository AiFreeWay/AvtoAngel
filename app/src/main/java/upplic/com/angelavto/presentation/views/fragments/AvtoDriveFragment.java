package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoDriveCtrl;
import upplic.com.angelavto.presentation.views.activities.MainActivity;


public class AvtoDriveFragment extends BaseFragment<FmtAvtoDriveCtrl> {

    @BindView(R.id.fmt_avto_drive_tv_warning)
    TextView mTvWarning;
    @BindView(R.id.fmt_avto_drive_swt_security)
    SwitchCompat mSwtStatus;
    @BindView(R.id.fmt_avto_drive_swt_notifications)
    SwitchCompat mSwtNotification;
    @BindView(R.id.fmt_avto_drive_btn_update)
    Button mBtnUpdate;

    private int mColorMarron;
    private int mColorSlateGray;

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
        setHasOptionsMenu(true);
        mViewController = new FmtAvtoDriveCtrl(this);
        mParentFragment = (AvtoFragment) getParentFragment();
        mColorMarron = ContextCompat.getColor(getContext(), R.color.marron);
        mColorSlateGray = ContextCompat.getColor(getContext(), R.color.slate_gray);
        mSwtStatus.setOnClickListener(v  -> mViewController.changeState(mSwtStatus.isChecked()));
        mSwtNotification.setOnClickListener(v -> mViewController.changeNotification(mSwtNotification.isChecked()));
        mBtnUpdate.setOnClickListener(v -> refresh());
    }

    @Override
    public void onStart() {
        super.onStart();
        notifyFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.avto_menu_edit)
            mViewController.openEditAvtoActivity();
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.avto_menu, menu);
    }

    @Override
    public void refresh() {
        super.refresh();
        mParentFragment.refresh();
    }

    public Car getCar() {
        return mParentFragment.getCar();
    }

    public CarOptions getCarOptions() {
        return mParentFragment.getCarOptions();
    }

    public void notifyFragment() {
        if (getCar() != null) {
            initStatusButton();
            initNotificationButton();
        }
    }

    public void setNotificationValue(boolean isChecked) {
        String message;
        if (isChecked)
            message  = "Оповещения для '"+getCarOptions().getTitle()+"' включены.";
        else
            message  = "Оповещения для '"+getCarOptions().getTitle()+"' отключены.";
        setSwitchTextColor(mSwtNotification, isChecked, message);
    }

    public void setStatusValue(boolean isChecked) {
        String message;
        if (isChecked)
            message  = "Охрана для '"+getCar().getTitle()+"' включена.";
        else
            message  = "Охрана для '"+getCar().getTitle()+"' отключена.";
        setSwitchTextColor(mSwtStatus, isChecked, message);
    }

    private void setSwitchTextColor(SwitchCompat switchView, boolean isChecked, String message) {
        if (isChecked)
            switchView.setTextColor(mColorSlateGray);
        else
            switchView.setTextColor(mColorMarron);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void initStatusButton() {
        mSwtStatus.setEnabled(true);
        if (getCar().isStatus())
            mSwtStatus.setChecked(true);
        else
            mSwtStatus.setTextColor(mColorMarron);
    }

    private void initNotificationButton() {
        mSwtNotification.setEnabled(true);
        if (getCarOptions().isNotification())
            mSwtNotification.setChecked(true);
        else
            mSwtNotification.setTextColor(mColorMarron);
    }
}
