package upplic.com.angelavto.presentation.views.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action0;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.utils.PhoneNumberTextWatcher;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class InputPhoneFragment extends BaseFragment {

    @BindView(R.id.fmt_input_phone_et_number)
    EditText mEtNumber;
    @BindView(R.id.fmt_input_phone_btn_continue)
    Button mBtnContinue;

    private LoginActivity mActivity;
    private Drawable mDrawableOnButtonEnabled;
    private Drawable mDrawableOnButtonDisabled;
    private int mColorOnButtonEnabled;
    private int mColorOnButtonDisabled;
    private PhoneNumberTextWatcher mPhoneNumberMask;

    private PhoneNumberTextWatcher.AfterTextChangeListener mActionWather = editable -> {
        if (editable.length() == PhoneNumberTextWatcher.PHONE_NUMBER_LENGTH)
            enabledButton();
        else
            disabledButton();
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_input_phone, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (LoginActivity) getBaseActivity();

        mDrawableOnButtonEnabled = ContextCompat.getDrawable(getContext(), R.drawable.selector_green_button);
        mDrawableOnButtonDisabled = ContextCompat.getDrawable(getContext(), R.drawable.button_green_disabled);
        mColorOnButtonEnabled = ContextCompat.getColor(getContext(), R.color.grideperlevy);
        mColorOnButtonDisabled = ContextCompat.getColor(getContext(), R.color.silver_gray);
        mPhoneNumberMask = new PhoneNumberTextWatcher(mEtNumber);

        mPhoneNumberMask.setAfterTextChangeListener(mActionWather);
        mEtNumber.addTextChangedListener(mPhoneNumberMask);
        mBtnContinue.setOnClickListener(v -> nextSlide());
        mEtNumber.setText(mActivity.getNubmer());
    }

    private void nextSlide() {
        mActivity.goToGetCodeSlide(mEtNumber.getText().toString());
    }

    private void enabledButton() {
        mBtnContinue.setEnabled(true);
        mBtnContinue.setBackground(mDrawableOnButtonEnabled);
        mBtnContinue.setTextColor(mColorOnButtonEnabled);
    }

    private void disabledButton() {
        mBtnContinue.setEnabled(false);
        mBtnContinue.setBackground(mDrawableOnButtonDisabled);
        mBtnContinue.setTextColor(mColorOnButtonDisabled);
    }
}