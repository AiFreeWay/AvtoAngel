package upplic.com.angelavto.presentation.utils;


import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

public class PhoneNumberTextWatcher implements TextWatcher {

    public static final int PHONE_NUMBER_LENGTH = 17;

    private EditText mEditText;
    private String mLastText;
    private AfterTextChangeListener mAfterTextChangeListener;

    public PhoneNumberTextWatcher(EditText editText) {
        mEditText = editText;
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(PHONE_NUMBER_LENGTH)});
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
        if (mAfterTextChangeListener != null)
            mAfterTextChangeListener.execute(editable);
    }

    public void setAfterTextChangeListener(AfterTextChangeListener listener) {
        mAfterTextChangeListener = listener;
    }

    private String getNumberFromText(String text) {
        StringBuilder number = new StringBuilder();
        String simbols = text.replaceAll("[^0-9]", "");
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

    public interface AfterTextChangeListener {

        void execute(Editable editable);
    }
}
