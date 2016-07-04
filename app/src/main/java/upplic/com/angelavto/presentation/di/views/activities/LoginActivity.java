package upplic.com.angelavto.presentation.di.views.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.di.view_controllers.AcLoginCtrl;

public class LoginActivity extends BaseActivity<AcLoginCtrl> {

    @BindView(R.id.ac_login_tv_description)
    TextView mtvDescription;
    @BindView(R.id.ac_login_btn_enter)
    Button mBtnEnter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
        appendPhoneNumber();
        mViewController = new AcLoginCtrl(this);
        mBtnEnter.setOnClickListener(v -> mViewController.startMainActivity());
    }

    private void appendPhoneNumber() {
        int greenColor = ContextCompat.getColor(this, R.color.green_jungle_krayola);
        SpannableString phone = new SpannableString(" +7 (999) 964-07-66");
        phone.setSpan(new ForegroundColorSpan(greenColor), 0 ,phone.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mtvDescription.append(phone);
    }
}
