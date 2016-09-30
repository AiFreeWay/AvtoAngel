package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoDriveCtrl;


public class AvtoDriveFragment extends BaseFragment<FmtAvtoDriveCtrl> {

    @BindView(R.id.fmt_avto_drive_tv_warning)
    TextView mTvWarning;
    @BindView(R.id.fmt_avto_drive_btn_security)
    Button mBtnStatus;
    @BindView(R.id.fmt_avto_drive_btn_notification)
    Button mBtnNotification;
    @BindView(R.id.fmt_avto_drive_btn_update)
    Button mBtnUpdate;
    @BindView(R.id.fmt_avto_drive_btn_alarm_off)
    Button mBtnAlarmOff;

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
        setHasOptionsMenu(true);
        mViewController = new FmtAvtoDriveCtrl(this);
        mParentFragment = (AvtoFragment) getParentFragment();
        if (mParentFragment.getAlarm() != null)
            mTvWarning.setText(mParentFragment.getAlarm().getTitle());
        mColorMarron = ContextCompat.getColor(getContext(), R.color.marron);
        mColorGreen = ContextCompat.getColor(getContext(), R.color.green_jungle_krayola);
        mBtnStatus.setOnClickListener(v -> mViewController.changeState());
        mBtnNotification.setOnClickListener(v -> mViewController.changeNotification());
        mBtnUpdate.setOnClickListener(v -> refresh());
        mBtnAlarmOff.setOnClickListener(v -> mViewController.offAlarm());
        initAlarmOffButton();
    }

    @Override
    public void onStart() {
        super.onStart();
        notifyFragment();
    }

    @Override
    public void refresh() {
        super.refresh();
        mParentFragment.refresh();
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

    public void initStatusButton() {
        mBtnStatus.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_marengo_button));
        if (getCar().isStatus()) {
            mBtnStatus.setTextColor(mColorGreen);
            mBtnStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_green, 0, 0, 0);
        } else {
            mBtnStatus.setTextColor(mColorMarron);
            mBtnStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_red, 0, 0, 0);
        }
    }

    public void initNotificationButton() {
        mBtnNotification.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_marengo_button));
        if (getCarOptions().isNotification()) {
            mBtnNotification.setTextColor(mColorGreen);
            mBtnNotification.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications_green, 0, 0, 0);
        } else {
            mBtnNotification.setTextColor(mColorMarron);
            mBtnNotification.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications_red, 0, 0, 0);
        }
    }

    public void initAlarmOffButton() {
        if (Hawk.contains(AvtoFragment.ALARM_WARNING_TAG)) {
            mBtnAlarmOff.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_marron_button));
            mBtnAlarmOff.setOnClickListener(v -> mViewController.offAlarm());
            mBtnAlarmOff.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            mBtnAlarmOff.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_alarm_white, 0, 0, 0);
        } else {
            mBtnAlarmOff.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_marron_disabled));
            mBtnAlarmOff.setOnClickListener(v -> {});
            mBtnAlarmOff.setTextColor(ContextCompat.getColor(getContext(), R.color.silver_gray));
            mBtnAlarmOff.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_alarm, 0, 0, 0);
        }
    }
}