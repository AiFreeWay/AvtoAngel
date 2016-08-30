package upplic.com.angelavto.presentation.views.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;
import com.rey.material.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.utils.PhoneNumberTextWatcher;
import upplic.com.angelavto.presentation.view_controllers.FmtCreateCarCtrl;
import upplic.com.angelavto.presentation.views.activities.MainActivity;


public class CreateCarFragment extends BaseFragment<FmtCreateCarCtrl> {

    @BindView(R.id.fmt_create_car_btn_create_car)
    Button mBtnCreateCar;
    @BindView(R.id.fmt_create_car_et_car)
    EditText mEtTitle;
    @BindView(R.id.fmt_create_car_et_phone)
    EditText mEtPhone;
    @BindView(R.id.fmt_create_car_pv_progress)
    ProgressView mPvProgress;
    @BindView(R.id.fmt_create_car_spn_beacon_type)
    Spinner mSpnBeaconType;

    private PhoneNumberTextWatcher mPhoneNumberMask;
    private int mColorOnButtonEnabled;
    private int mColorOnButtonDisabled;
    private Drawable mDrawableOnButtonEnabled;
    private Drawable mDrawableOnButtonDisabled;
    private ArrayAdapter<Beacon> mAdapter;

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
        mPhoneNumberMask = new PhoneNumberTextWatcher(mEtPhone);
        mDrawableOnButtonEnabled = ContextCompat.getDrawable(getContext(), R.drawable.selector_marengo_button);
        mDrawableOnButtonDisabled = ContextCompat.getDrawable(getContext(), R.drawable.button_marengo_disabled);
        mColorOnButtonEnabled = ContextCompat.getColor(getContext(), R.color.grideperlevy);
        mColorOnButtonDisabled = ContextCompat.getColor(getContext(), R.color.silver_gray);
        mEtPhone.addTextChangedListener(mPhoneNumberMask);
        mBtnCreateCar.setOnClickListener(v -> doOnCreateCar());
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getBaseActivity().getSupportActionBar().setTitle(R.string.add_car);
            ((MainActivity) getBaseActivity()).getToolbar().getMenu().clear();
        } catch (NullPointerException e) {

        }
    }

    @Override
    public void refresh() {
        super.refresh();
        getBaseActivity().refresh();
        mViewController.start();
    }

    public void truncateFields() {
        mEtTitle.setText("");
        mEtPhone.setText("");
    }

    public void showStartLoad() {
        mPvProgress.start();
        mBtnCreateCar.setEnabled(false);
        mBtnCreateCar.setBackground(mDrawableOnButtonDisabled);
        mBtnCreateCar.setTextColor(mColorOnButtonDisabled);
    }

    public void showSuccesLoad() {
        mPvProgress.stop();
        mBtnCreateCar.setEnabled(true);
        mBtnCreateCar.setBackground(mDrawableOnButtonEnabled);
        mBtnCreateCar.setTextColor(mColorOnButtonEnabled);
    }

    public void showDeniedLoad(int messageResiId) {
        Toast.makeText(getContext(), R.string.cant_load_data, Toast.LENGTH_SHORT).show();
        mPvProgress.stop();
    }

    public void loadData(List<Beacon> beacons) {
        mAdapter = new ArrayAdapter<>(getBaseActivity().getApplicationContext(), R.layout.v_spinner_item, beacons);
        mSpnBeaconType.setAdapter(mAdapter);
    }

    private void doOnCreateCar() {
        String title = mEtTitle.getText().toString();
        String phone = mEtPhone.getText().toString();
        if (title.isEmpty() || phone.isEmpty() || phone.length() != PhoneNumberTextWatcher.PHONE_NUMBER_LENGTH)
            Toast.makeText(getContext(), R.string.need_fill_fields, Toast.LENGTH_SHORT).show();
        else
            mViewController.addCar(getCar());
    }

    private Car getCar() {
        Car car = new Car();
        car.setTitle(mEtTitle.getText().toString());
        car.setTrackerNumber(mEtPhone.getText().toString());
        int beaconType = mAdapter.getItem(mSpnBeaconType.getSelectedItemPosition()).getId();
        car.setTrackerType(beaconType);
        return car;
    }
}
