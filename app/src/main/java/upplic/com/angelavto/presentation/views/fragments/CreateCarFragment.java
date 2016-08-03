package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.utils.PhoneNumberTextWatcher;
import upplic.com.angelavto.presentation.view_controllers.FmtCreateCarCtrl;


public class CreateCarFragment extends BaseFragment<FmtCreateCarCtrl> {

    @BindView(R.id.fmt_create_btn_create_car)
    Button mBtnCreateCar;
    @BindView(R.id.fmt_create_et_car)
    EditText mEtTitle;
    @BindView(R.id.fmt_create_et_phone)
    EditText mEtPhone;

    private PhoneNumberTextWatcher mPhoneNumberMask;

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
        mEtPhone.addTextChangedListener(mPhoneNumberMask);
        mBtnCreateCar.setOnClickListener(v -> doOnCreateCar());
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        getBaseActivity().getSupportActionBar().setTitle(R.string.add_car);
    }

    public void truncateFields() {
        mEtTitle.setText("");
        mEtPhone.setText("");
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
        car.setPhone(mEtPhone.getText().toString());
        car.setNotificationState(Car.NOTIFICATION_OFF);
        car.setSequrityState(Car.STATE_UNLOCK);
        return car;
    }
}
