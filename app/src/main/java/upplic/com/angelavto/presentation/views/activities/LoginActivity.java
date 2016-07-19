package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rey.material.app.Dialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;

public class LoginActivity extends BaseActivity<AcLoginCtrl> {

    @BindView(R.id.ac_login_tv_description)
    TextView mTvDescription;
    @BindView(R.id.ac_login_et_code)
    EditText mEtCode;
    @BindView(R.id.ac_login_btn_enter)
    Button mBtnEnter;

    private Dialog mDialog;
    private Dialog mDialogInputNumber;
    private String mNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
        mDialog = new Dialog(this, R.style.login_dialog)
                .neutralAction(R.string.ok)
                .neutralActionClickListener(v -> mDialog.dismiss())
                .cancelable(true);

        mDialogInputNumber = new Dialog(this, R.style.login_dialog)
                .title(R.string.ac_login_input_phone)
                .positiveAction(R.string.input)
                .positiveActionClickListener(v -> addNumber())
                .contentView(R.layout.v_input_phone_dialog)
                .cancelable(true);
        mViewController = new AcLoginCtrl(this);
        mBtnEnter.setOnClickListener(v -> mViewController.startMainActivity());
        mTvDescription.setOnClickListener(v -> mDialogInputNumber.show());
    }

    public void showNeutralDialog(@StringRes int resId) {
        mDialog.title(resId)
                .show();
    }

    private void addNumber() {
        EditText etInputPhone = (EditText) mDialogInputNumber.findViewById(R.id.v_input_phone_dialog_et_number);
        String number = etInputPhone.getText().toString();
        if (!number.isEmpty()) {
            appendPhoneNumber(number);
            mNumber = number;
            mDialogInputNumber.dismiss();
        }
    }

    private void appendPhoneNumber(String phoneNumber) {
        int greenColor = ContextCompat.getColor(this, R.color.green_jungle_krayola);
        SpannableString phone = new SpannableString(" "+phoneNumber);
        phone.setSpan(new ForegroundColorSpan(greenColor), 0 ,phone.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvDescription.setText(R.string.ac_login_description);
        mTvDescription.append(phone);
    }
}
