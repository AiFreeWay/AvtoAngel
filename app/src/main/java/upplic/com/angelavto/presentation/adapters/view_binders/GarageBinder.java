package upplic.com.angelavto.presentation.adapters.view_binders;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action0;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.view_controllers.FmtGarageCtrl;

public class GarageBinder implements AbstractBinder<Car> {

    @BindView(R.id.v_garage_tv_title)
    TextView mTvTitle;
    @BindView(R.id.v_garage_swt_notifications)
    SwitchCompat mSwtNotifications;
    @BindView(R.id.v_garage_swt_security)
    SwitchCompat mSwtSecurity;

    private FmtGarageCtrl mViewController;
    private ListView mParent;
    private LayoutInflater mLayoutInflater;

    public GarageBinder(FmtGarageCtrl controller) {
        mViewController = controller;
        mParent = mViewController.getRootView().getLvCars();
        mLayoutInflater = mViewController.getLayoutInflater();
    }

    @Override
    public View bind(View view, Car data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_garage, mParent, false);
        ButterKnife.bind(this, view);
        mTvTitle.setText(data.getTitle());
        view.setOnClickListener(v -> mViewController.openAvtoActivity(data));
        initSwitchListeners(data);
        return view;
    }

    private void initSwitchListeners(Car data) {
        mSwtSecurity.setOnCheckedChangeListener(null);
        mSwtNotifications.setOnCheckedChangeListener(null);
        proccessCarState(data.getSequrityState());
        proccessCarNotification(data.getNotificationState());
        mSwtSecurity.setOnCheckedChangeListener(new SwitchStateListener(data, SwitchStateListener.TYPE_SEQURITY));
        mSwtNotifications.setOnCheckedChangeListener(new SwitchStateListener(data, SwitchStateListener.TYPE_NOTIFICATION));
    }

    private void proccessCarNotification(int notificationState) {
        boolean isNotificationOn = notificationState == Car.NOTIFICATION_ON;
        setState(mSwtNotifications, isNotificationOn);
    }

    private void proccessCarState(int state) {
        boolean isLock = state == Car.STATE_LOCK;
        setState(mSwtSecurity, isLock);
    }

    private void setState(CompoundButton view, boolean state) {
        view.setChecked(state);
        onSwitchStateChange(view, state);
    }

    private void setSwitchChecked(CompoundButton view) {
        view.setTextColor(ContextCompat.getColor(mViewController.getRootView().getContext(), R.color.slate_gray));
    }

    private void setSwitchUnchecked(CompoundButton view) {
        view.setTextColor(ContextCompat.getColor(mViewController.getRootView().getContext(), R.color.marron));
    }

    private void onSwitchStateChange(CompoundButton view, boolean state) {
        if (state)
            setSwitchChecked(view);
        else
            setSwitchUnchecked(view);
    }

    private class SwitchStateListener implements CompoundButton.OnCheckedChangeListener {

        public static final int TYPE_SEQURITY = 0;
        public static final int TYPE_NOTIFICATION = 1;

        private Car mCar;
        private int mType;

        public SwitchStateListener(Car car, int type) {
            mType = type;
            mCar = car;
        }

        @Override
        public void onCheckedChanged(CompoundButton view, boolean state) {
            onSwitchStateChange(view, state);
            changeModelValue(state);
            mViewController.hundleClick(mCar);
        }

        private void changeModelValue(boolean state) {
            if (mType == TYPE_SEQURITY)
                changeSequrity(state);
            else
                changeNotification(state);
        }


        private void changeNotification(boolean state) {
            if (state)
                mCar.setNotificationState(Car.NOTIFICATION_ON);
            else
                mCar.setNotificationState(Car.NOTIFICATION_OFF);
        }

        private void changeSequrity(boolean state) {
            if (state)
                mCar.setSequrityState(Car.STATE_LOCK);
            else
                mCar.setSequrityState(Car.STATE_UNLOCK);
        }
    }
}
