package upplic.com.angelavto.presentation.views.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.maskedphoneedittext.MaskedPhoneEditText;
import com.example.maskedphoneedittext.PhoneNumberTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class InputPhoneFragment extends BaseFragment {

    @BindView(R.id.fmt_input_phone_et_number)
    MaskedPhoneEditText mEtNumber;
    @BindView(R.id.fmt_input_phone_btn_continue)
    Button mBtnContinue;
    @BindView(R.id.fmt_input_phone_root)
    ViewGroup mVgRoot;

    private LoginActivity mActivity;
    private Drawable mDrawableOnButtonEnabled;
    private Drawable mDrawableOnButtonDisabled;
    private int mColorOnButtonEnabled;
    private int mColorOnButtonDisabled;
    private InputMethodManager mInputMethodManager;

    private com.example.maskedphoneedittext.PhoneNumberTextWatcher.AfterTextChangeListener mActionWather = editable -> {
        if (editable.length() == PhoneNumberTextWatcher.PHONE_NUMBER_LENGTH) {
            enabledButton();
            mInputMethodManager.hideSoftInputFromWindow(mEtNumber.getWindowToken(), 0);
        } else
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

        mInputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mDrawableOnButtonEnabled = ContextCompat.getDrawable(getContext(), R.drawable.selector_green_button);
        mDrawableOnButtonDisabled = ContextCompat.getDrawable(getContext(), R.drawable.button_disabled);
        mColorOnButtonEnabled = ContextCompat.getColor(getContext(), R.color.grideperlevy);
        mColorOnButtonDisabled = ContextCompat.getColor(getContext(), R.color.silver_gray);
        mEtNumber.setAfterTextChangeListener(mActionWather);
        mBtnContinue.setOnClickListener(v -> nextSlide());
        mEtNumber.setText(mActivity.getNubmer());
        mVgRoot.setOnTouchListener((view, motionEvent) -> {
            mInputMethodManager.hideSoftInputFromWindow(mEtNumber.getWindowToken(), 0);
            return true;
        });
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