package com.example.maskedphoneedittext;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
        bindViews();
    }

    public MaskedPhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setContentView(R.layout.v_maskedphone);
        bindViews();
        initAttributes(context, attrs);
    }

    public MaskedPhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContentView(R.layout.v_maskedphone);
        bindViews();
        initAttributes(context, attrs);
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

    private void bindViews() {
        mEtNumber = (EditText) findViewById(R.id.v_maskedphone_et_number);
        mTvBackground = (TextView) findViewById(R.id.v_maskedphone_tv_background);
        mMaskPhoneWatcher = new PhoneNumberTextWatcher(mEtNumber, mTvBackground);
        mEtNumber.addTextChangedListener(mMaskPhoneWatcher);
    }

    private void initAttributes(Context context, AttributeSet rawAttrs) {
        TypedArray attributes = context.obtainStyledAttributes(rawAttrs, R.styleable.MaskedPhoneAttrs);
        CharSequence hint = attributes.getString(R.styleable.MaskedPhoneAttrs_hint);
        if (hint != null)
            mEtNumber.setHint(hint);

        int textColor = attributes.getColor(R.styleable.MaskedPhoneAttrs_textColor, ContextCompat.getColor(context, android.R.color.black));
        if (textColor != -16777216)
            mEtNumber.setTextColor(textColor);

        int hintColor = attributes.getColor(R.styleable.MaskedPhoneAttrs_hintColor, ContextCompat.getColor(context, android.R.color.black));
        if (hintColor != -16777216)
            mEtNumber.setHintTextColor(hintColor);

        int templateColor = attributes.getColor(R.styleable.MaskedPhoneAttrs_templateColor, ContextCompat.getColor(context, android.R.color.black));
        if (templateColor != -16777216)
            mTvBackground.setTextColor(templateColor);
        attributes.recycle();
    }
}
