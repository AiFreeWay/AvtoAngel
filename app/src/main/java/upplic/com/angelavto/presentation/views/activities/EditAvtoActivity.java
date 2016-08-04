package upplic.com.angelavto.presentation.views.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
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
    @BindView(R.id.ac_edit_avto_et_car_title)
    EditText mEtCarTitle;
    @BindView(R.id.ac_edit_avto_et_phone)
    EditText mEtPhone;
    @BindView(R.id.ac_edit_avto_btn_delete)
    Button mBtnDelete;
    @BindView(R.id.ac_edit_avto_btn_save)
    Button mBtnSave;

    private Car mCar;
    private Dialog mMessageDialog;
    private MaterialMenuIconToolbar mMenuDrawer;
    private int mColorMarron;
    private int mColorGreenJungleKrayola;
    private PhoneNumberTextWatcher mPhoneNumberMask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_edit_avto);
        ButterKnife.bind(this);

        mCar = (Car) getIntent().getSerializableExtra(CAR_TAG);
        mColorMarron = ContextCompat.getColor(this, R.color.marron);
        mColorGreenJungleKrayola = ContextCompat.getColor(this, R.color.green_jungle_krayola);
        mViewController = new AcEditAvtoCtrl(this);
        mPhoneNumberMask = new PhoneNumberTextWatcher(mEtPhone);
        mEtPhone.addTextChangedListener(mPhoneNumberMask);

        initToolbar();
        getSupportActionBar().setTitle(R.string.edit);
        mEtCarTitle.setText(mCar.getTitle());
        mEtPhone.setText(mCar.getPhone());
        mBtnDelete.setOnClickListener(v -> onDelete());
        mBtnSave.setOnClickListener(v -> onSave());
        mMessageDialog = new Dialog(this, R.style.login_dialog)
                .titleColor(ContextCompat.getColor(this, R.color.slate_gray))
                .actionTextColor(mColorGreenJungleKrayola)
                .positiveAction(R.string.yes)
                .negativeAction(R.string.no)
                .negativeActionClickListener(v -> mMessageDialog.dismiss());
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
            mMessageDialog.dismiss();
        });
        mMessageDialog.show();
    }

    private void onSave() {
        mMessageDialog.setTitle(R.string.save_confirm);
        mMessageDialog.positiveActionTextColor(mColorGreenJungleKrayola);
        mMessageDialog.positiveActionClickListener(v -> {
            if (isFieldsCorrect())
                mViewController.updateCar(updateCarFromFields());
            else
                Toast.makeText(this, R.string.need_fill_fields, Toast.LENGTH_SHORT).show();
            mMessageDialog.dismiss();});
        mMessageDialog.show();
    }

    private Car updateCarFromFields() {
        mCar.setTitle(mEtCarTitle.getText().toString());
        mCar.setPhone(mEtPhone.getText().toString());
        return mCar;
    }

    private boolean isFieldsCorrect() {
        String title = mEtCarTitle.getText().toString();
        String phone = mEtPhone.getText().toString();
        return !(title.isEmpty() || phone.isEmpty() || phone.length() != PhoneNumberTextWatcher.PHONE_NUMBER_LENGTH);
    }
}
