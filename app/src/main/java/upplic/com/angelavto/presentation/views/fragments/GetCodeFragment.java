package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rey.material.app.Dialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.FmtGetCodeCtrl;

public class GetCodeFragment extends BaseFragment<FmtGetCodeCtrl> {

    @BindView(R.id.fmt_login_tv_description)
    TextView mTvDescription;
    @BindView(R.id.fmt_login_et_code)
    EditText mEtCode;
    @BindView(R.id.fmt_login_btn_enter)
    Button mBtnEnter;
    @BindView(R.id.fmt_login_btn_get_code)
    Button mBtnGetCode;

    private Dialog mDialog;
    private Dialog mDialogInputNumber;
    private String mNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_get_code, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog = new Dialog(getBaseActivity(), R.style.login_dialog)
                .neutralAction(R.string.ok)
                .neutralActionClickListener(v -> mDialog.dismiss())
                .cancelable(true);

        mDialogInputNumber = new Dialog(getBaseActivity(), R.style.login_dialog)
                .title(R.string.fmt_login_input_phone)
                .positiveAction(R.string.input)
                .positiveActionClickListener(v -> addNumber())
                .contentView(R.layout.v_input_phone_dialog)
                .cancelable(true);
        mViewController = new FmtGetCodeCtrl(this);
        mBtnEnter.setOnClickListener(v -> mViewController.startMainActivity());
        mTvDescription.setOnClickListener(v -> mDialogInputNumber.show());
        mBtnGetCode.setOnClickListener(v -> getCode());
    }

    public void showNeutralDialog(@StringRes int resId) {
        mDialog.title(resId)
                .show();
    }

    private void addNumber() {
        EditText etInputPhone = (EditText) mDialogInputNumber.findViewById(R.id.v_input_phone_dialog_et_number);
        String number = etInputPhone.getText().toString();
        if (!number.isEmpty()) {
            appendPhoneNumber(R.string.phone_number, number);
            mNumber = number;
            mDialogInputNumber.dismiss();
        }
    }

    private void getCode() {
        if (mNumber != null && !mNumber.isEmpty())
            appendPhoneNumber(R.string.fmt_login_description, mNumber);
    }

    private void appendPhoneNumber(@StringRes int testRes,  String phoneNumber) {
        int greenColor = ContextCompat.getColor(getBaseActivity(), R.color.green_jungle_krayola);
        SpannableString phone = new SpannableString(" "+phoneNumber);
        phone.setSpan(new ForegroundColorSpan(greenColor), 0 ,phone.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvDescription.setText(testRes);
        mTvDescription.append(phone);
    }
}
