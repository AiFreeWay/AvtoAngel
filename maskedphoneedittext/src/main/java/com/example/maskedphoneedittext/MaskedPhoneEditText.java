package com.example.maskedphoneedittext;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MaskedPhoneEditText extends RelativeLayout {

    private EditText mEtNumber;
    private TextView mTvBackground;
    private TextView mTvPlus;
    private ImageView mIvImage;
    private PhoneNumberTextWatcher mMaskPhoneWatcher;

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

    public void setAfterTextChangeListener(PhoneNumberTextWatcher.AfterTextChangeListener listener) {
        mMaskPhoneWatcher.setAfterTextChangeListener(listener);
    }

    private void setContentView(int id) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(id, this);
    }

    private void bindViews() {
        mEtNumber = (EditText) findViewById(R.id.v_maskedphone_et_number);
        mTvBackground = (TextView) findViewById(R.id.v_maskedphone_tv_background);
        mTvPlus = (TextView) findViewById(R.id.v_maskedphone_tv_plus);
        mIvImage = (ImageView) findViewById(R.id.v_maskedphone_iv_image);
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

        Drawable drawable = attributes.getDrawable(R.styleable.MaskedPhoneAttrs_drawableLeft);
        if (drawable != null)
            mIvImage.setImageDrawable(drawable);

        float drawablePadding = attributes.getDimensionPixelSize(R.styleable.MaskedPhoneAttrs_drawablePadding, -1);
        if (drawablePadding != -1)
            mIvImage.setPadding(0, 0, (int) drawablePadding, 0);

        String fontFamily = attributes.getString(R.styleable.MaskedPhoneAttrs_fontFamily);
        if (fontFamily != null) {
            Typeface fontStyle = Typeface.create(fontFamily, Typeface.NORMAL);
            mEtNumber.setTypeface(fontStyle);
            mTvBackground.setTypeface(fontStyle);
            mTvPlus.setTypeface(fontStyle);
        }

        float templateSize = attributes.getDimension(R.styleable.MaskedPhoneAttrs_templateFontSize, -1);
        if (templateSize != -1)
            mTvBackground.setTextSize(TypedValue.COMPLEX_UNIT_PX, templateSize);

        float textSize = attributes.getDimension(R.styleable.MaskedPhoneAttrs_textFontSize, -1);
        if (textSize != -1)
            mEtNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        attributes.recycle();
    }
}
