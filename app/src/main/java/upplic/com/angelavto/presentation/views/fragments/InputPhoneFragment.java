package upplic.com.angelavto.presentation.views.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class InputPhoneFragment extends BaseFragment {

    private final int PHONE_NUMBER_LENGTH = 17;

    @BindView(R.id.fmt_input_phone_et_number)
    EditText mEtNumber;
    @BindView(R.id.fmt_input_phone_btn_continue)
    Button mBtnContinue;

    private LoginActivity mActivity;
    private Drawable mDrawableOnButtonEnabled;
    private Drawable mDrawableOnButtonDisabled;
    private int mColorOnButtonEnabled;
    private int mColorOnButtonDisabled;

    private TextWatcher mNuberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == PHONE_NUMBER_LENGTH)
                enabledButton();
            else
                disabledButton();
        }
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

        mEtNumber.addTextChangedListener(mNuberWatcher);
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
