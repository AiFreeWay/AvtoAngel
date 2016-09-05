package com.example.maskedphoneedittext;


import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MaskedPhoneEditText extends FrameLayout {

    private EditText mEtNumber;
    private TextView mTvBackground;
    private TextWatcher mMaskPhoneWatcher;

    public MaskedPhoneEditText(Context context) {
        super(context);
        setContentView(R.layout.v_maskedphone);
        init();
    }

    public MaskedPhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setContentView(R.layout.v_maskedphone);
        init();
    }

    public MaskedPhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContentView(R.layout.v_maskedphone);
        init();
    }

    public void setText(String text) {
        mEtNumber.setText(text);
    }

    public String getText() {
        return mEtNumber.getText().toString();
    }

    private void setContentView(int id) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(id, this);
    }

    private void init() {
        mEtNumber = (EditText) findViewById(R.id.v_maskedphone_et_number);
        mTvBackground = (TextView) findViewById(R.id.v_maskedphone_tv_background);
        mMaskPhoneWatcher = new PhoneNumberTextWatcher(mEtNumber, mTvBackground);
        mEtNumber.addTextChangedListener(mMaskPhoneWatcher);
    }
}
