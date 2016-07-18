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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
        appendPhoneNumber();
        mDialog = new Dialog(this, R.style.login_dialog)
                .neutralAction(R.string.ok)
                .neutralActionClickListener(v -> mDialog.dismiss())
                .cancelable(true);
        mViewController = new AcLoginCtrl(this);
        mBtnEnter.setOnClickListener(v -> mViewController.startMainActivity());
    }

    private void appendPhoneNumber() {
        int greenColor = ContextCompat.getColor(this, R.color.green_jungle_krayola);
        SpannableString phone = new SpannableString(" +7 (999) 964-07-66");
        phone.setSpan(new ForegroundColorSpan(greenColor), 0 ,phone.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvDescription.append(phone);
    }

    public String getCode() {
        return mEtCode.getText().toString();
    }

    public void showNeutralDialog(@StringRes int resId) {
        mDialog.title(resId)
                .show();
    }
}
