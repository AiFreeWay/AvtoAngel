package upplic.com.angelavto.presentation.views.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.rey.material.app.Dialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.utils.PhoneNumberTextWatcher;
import upplic.com.angelavto.presentation.view_controllers.AcEditAvtoCtrl;


public class EditAvtoActivity extends BaseActivity<AcEditAvtoCtrl> {

    public static final String CAR_TAG = "car";

    @BindView(R.id.ac_edit_avto_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ac_edit_avto_til_car_title)
    TextInputLayout mIlCar;
    @BindView(R.id.ac_edit_avto_til_imei)
    TextInputLayout mIlImei;
    @BindView(R.id.ac_edit_avto_til_phone)
    TextInputLayout mIlPhone;
    @BindView(R.id.ac_edit_avto_et_car_title)
    EditText mEtCarTitle;
    @BindView(R.id.ac_edit_avto_et_imei)
    EditText mEtImei;
    @BindView(R.id.ac_edit_avto_et_phone)
    EditText mEtPhone;
    @BindView(R.id.ac_edit_avto_btn_delete)
    Button mBtnDelete;
    @BindView(R.id.ac_edit_avto_btn_save)
    Button mBtnSave;
    @BindView(R.id.ac_edit_avto_root)
    ViewGroup mVgRoot;

    private Car mCar;
    private Dialog mMessageDialog;
    private MaterialMenuIconToolbar mMenuDrawer;
    private int mColorMarron;
    private int mColorGreenJungleKrayola;
    private InputMethodManager mInputMethodManager;
    private PhoneNumberTextWatcher mPhoneNumberTextWatcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_edit_avto);
        ButterKnife.bind(this);

        mCar = (Car) getIntent().getSerializableExtra(CAR_TAG);
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mColorMarron = ContextCompat.getColor(this, R.color.marron);
        mColorGreenJungleKrayola = ContextCompat.getColor(this, R.color.green_jungle_krayola);
        mViewController = new AcEditAvtoCtrl(this);
        mPhoneNumberTextWatcher = new PhoneNumberTextWatcher(mEtPhone);

        initToolbar();
        getSupportActionBar().setTitle(R.string.edit);
        mEtPhone.addTextChangedListener(mPhoneNumberTextWatcher);
        mEtCarTitle.setText(mCar.getTitle());
        mEtImei.setText(mCar.getTrackerImei());
        mEtPhone.setText(mCar.getTrackerPhone());
        mBtnDelete.setOnClickListener(v -> onDelete());
        mBtnSave.setOnClickListener(v -> onSave());
        mVgRoot.setOnTouchListener((view, motionEvent) -> {
            mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;});
        mMessageDialog = new Dialog(this, R.style.login_dialog)
                .titleColor(ContextCompat.getColor(this, R.color.slate_gray))
                .actionTextColor(mColorGreenJungleKrayola)
                .positiveAction(R.string.yes)
                .negativeAction(R.string.no)
                .negativeActionClickListener(v -> mMessageDialog.dismiss());
    }

    public Car getCar() {
        return mCar;
    }

    public void setCar(Car mCar) {
        this.mCar = mCar;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setNavigationOnClickListener(v -> finish());
        mMenuDrawer = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.ac_edit_avto_toolbar;
            }
        };
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }

    private void onDelete() {
        mMessageDialog.setTitle(R.string.delete_confirm);
        mMessageDialog.positiveActionTextColor(mColorMarron);
        mMessageDialog.positiveActionClickListener(v -> {
            mViewController.deleteCar(mCar);
            mMessageDialog.dismiss();});
        mMessageDialog.show();
    }

    private void onSave() {
        mMessageDialog.setTitle(R.string.save_confirm);
        mMessageDialog.positiveActionTextColor(mColorGreenJungleKrayola);
        mMessageDialog.positiveActionClickListener(v -> {
            if (isFieldsCorrect()) {
                hideTextInputLayoutsErrors();
                mViewController.updateCar(generateCarFromFields());
            } else
                Toast.makeText(this, R.string.need_fill_fields, Toast.LENGTH_SHORT).show();
            mMessageDialog.dismiss();});
        mMessageDialog.show();
    }

    private Car generateCarFromFields() {
        Car car = new Car();
        car.setId(mCar.getId());
        car.setTitle(mEtCarTitle.getText().toString());
        car.setTrackerImei(mEtImei.getText().toString());
        car.setTrackerPhone(mEtPhone.getText().toString());
        car.setTrackerType(mCar.getTrackerType());
        car.setLat(mCar.getLat());
        car.setLon(mCar.getLon());
        car.setStatus(mCar.isStatus());
        car.setRecord(mCar.isRecord());
        return car;
    }

    private boolean isFieldsCorrect() {
        hideTextInputLayoutsErrors();
        String title = mEtCarTitle.getText().toString();
        String imei = mEtImei.getText().toString();
        String phone = mEtPhone.getText().toString();
        boolean isFieldFalid = true;
        if (title.isEmpty()) {
            mIlCar.setError(getString(R.string.input_car_title));
            isFieldFalid = false;
        }
        if (imei.isEmpty()) {
            mIlImei.setError(getString(R.string.input_imei));
            isFieldFalid = false;
        }
        if (phone.isEmpty() || phone.length() != PhoneNumberTextWatcher.PHONE_NUMBER_LENGTH) {
            mIlPhone.setError(getString(R.string.input_phone));
            isFieldFalid = false;
        }
        return isFieldFalid;
    }

    private void hideTextInputLayoutsErrors() {
        mIlCar.setError(null);
        mIlImei.setError(null);
        mIlPhone.setError(null);
    }
}
