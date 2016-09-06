package com.example.maskedphoneedittext;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class PhoneNumberTextWatcher implements TextWatcher {

    public static final int PHONE_NUMBER_LENGTH = 17;
    public static final String BACKGROUND_TEMPLATE = "+x(xxx) xxx xx xx";

    private EditText mEditText;
    private TextView mTextView;
    private String mLastText;
    private AfterTextChangeListener mAfterTextChangeListener;

    public PhoneNumberTextWatcher(EditText editText, TextView textView) {
        mEditText = editText;
        mTextView = textView;
        mEditText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(PHONE_NUMBER_LENGTH)});
        mTextView.setHint(BACKGROUND_TEMPLATE);
        mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mLastText == null || !(charSequence.length() == mLastText.length())) {
            mLastText = getNumberFromText(charSequence.toString());
            mEditText.setText(mLastText);
            mEditText.setSelection(mLastText.length());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEditText.getText().length() > 0)
            mTextView.setVisibility(View.VISIBLE);
        else
            mTextView.setVisibility(View.INVISIBLE);
        if (mAfterTextChangeListener != null)
            mAfterTextChangeListener.execute(editable);
    }

    public void setAfterTextChangeListener(AfterTextChangeListener listener) {
        mAfterTextChangeListener = listener;
    }

    private String getNumberFromText(String text) {
        StringBuilder number = new StringBuilder();
        String simbols = text.replaceAll("[^0-9]", "");
        setBackgroundTemplate(simbols.length());
        for (int i=0; i<simbols.length(); i++) {
            char simbol = simbols.charAt(i);
            number.append(maskSimbol(i, simbol));
        }
        return number.toString();
    }

    private String maskSimbol(int position, char simbol) {
        switch (position) {
            case 0:
                return "+"+simbol;
            case 1:
                return "("+simbol;
            case 4:
                return ") "+simbol;
            case 7:
                return " "+simbol;
            case 9:
                return " "+simbol;
            default:
                return simbol+"";
        }
    }

    private void setBackgroundTemplate(int position) {
        StringBuilder template = new StringBuilder();
        int templateSize = 0;
        for (int i=0; i<position; i++) {
            template.append(getLeftSizeTemplateSpaces(i));
            templateSize+=determineTemplateSize(i);
        }
        String rightSideTemplate = BACKGROUND_TEMPLATE.substring(templateSize, BACKGROUND_TEMPLATE.length());
        template.append(rightSideTemplate);
        mTextView.setHint(template);
    }

    private String getLeftSizeTemplateSpaces(int position) {
        switch (position) {
            case 0:
                return "\u00A0\u00A0\u00A0\u00A0";
            case 1:
                return "\u00A0\u00A0\u00A0\u00A0";
            case 4:
                return "\u00A0\u00A0\u00A0\u00A0";
            case 7:
                return "\u00A0\u00A0\u00A0";
            case 8:
                return "\u00A0\u00A0";
            case 9:
                return "\u00A0\u00A0\u00A0";
            default:
                return "\u00A0\u00A0";
        }
    }

    private int determineTemplateSize(int position) {
        switch (position) {
            case 0:
                return 2;
            case 1:
                return 2;
            case 4:
                return 3;
            case 7:
                return 2;
            case 9:
                return 2;
            default:
                return 1;
        }
    }


    public interface AfterTextChangeListener {

        void execute(Editable editable);
    }
}

