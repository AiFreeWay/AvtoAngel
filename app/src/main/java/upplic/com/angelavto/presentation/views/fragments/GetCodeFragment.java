package upplic.com.angelavto.presentation.views.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.view_controllers.FmtGetCodeCtrl;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class GetCodeFragment extends BaseFragment<FmtGetCodeCtrl> {

    private final int INTERVAL_LENGTH = 64;
    private final Calendar CALENDAR = new GregorianCalendar();

    @BindView(R.id.fmt_login_tv_description)
    TextView mTvDescription;
    @BindView(R.id.fmt_login_tv_repeated_response)
    TextView mTvPepeatedResponse;
    @BindView(R.id.fmt_login_et_code)
    EditText mEtCode;
    @BindView(R.id.fmt_login_btn_enter)
    Button mBtnEnter;
    @BindView(R.id.fmt_login_btn_get_code)
    Button mBtnGetCode;

    private LoginActivity mActivity;
    private Drawable mDrawableOnButtonEnabled;
    private Drawable mDrawableOnButtonDisabled;
    private int mColorOnButtonEnabled;
    private int mColorOnButtonDisabled;
    private Observable<Long> mTimer = Observable.interval(1, TimeUnit.SECONDS);
    private Subscription mTimerSubscription;
    private SimpleDateFormat mTimeFormatter = new SimpleDateFormat("mm:ss");

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
        mActivity = (LoginActivity) getBaseActivity();

        mDrawableOnButtonEnabled = ContextCompat.getDrawable(getContext(), R.drawable.selector_green_button);
        mDrawableOnButtonDisabled = ContextCompat.getDrawable(getContext(), R.drawable.button_green_disabled);
        mColorOnButtonEnabled = ContextCompat.getColor(getContext(), R.color.grideperlevy);
        mColorOnButtonDisabled = ContextCompat.getColor(getContext(), R.color.silver_gray);

        CALENDAR.setTimeInMillis(System.currentTimeMillis());
        mViewController = new FmtGetCodeCtrl(this);
        mBtnEnter.setOnClickListener(v -> mViewController.startMainActivity());
        mBtnGetCode.setOnClickListener(v -> doOnSendCode());
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.enableViewPageChange();
        disabledButton();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mTimerSubscription != null)
            mTimerSubscription.unsubscribe();
        mTvDescription.setText("");
        mTvPepeatedResponse.setText("");
    }

    private void doOnSendCode() {
        mTimerSubscription = mTimer
                .doOnUnsubscribe(() -> mBtnGetCode.setEnabled(true))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangeInterval);
        appendColorText(R.string.fmt_login_description, mActivity.getNubmer(), mTvDescription);
        mBtnGetCode.setEnabled(false);
        enabledButton();
    }

    private void onChangeInterval(Long time) {
        if (time == INTERVAL_LENGTH) {
            mTimerSubscription.unsubscribe();
            mTvPepeatedResponse.setText("");
        } else {
            int remainingTime = INTERVAL_LENGTH-time.intValue();
            String timeText = intToMinuteAndSeconds(remainingTime);
            appendColorText(R.string.repeated, timeText, mTvPepeatedResponse);
        }
    }

    private String intToMinuteAndSeconds(int time) {
        int minutes = time/60;
        int seconds = time-(minutes*60);
        CALENDAR.set(Calendar.MINUTE, minutes);
        CALENDAR.set(Calendar.SECOND, seconds);
        return mTimeFormatter.format(CALENDAR.getTimeInMillis());
    }

    private void appendColorText(@StringRes int textRes, String willColoredText, TextView textView) {
        int greenColor = ContextCompat.getColor(getContext(), R.color.green_jungle_krayola);
        SpannableString phone = new SpannableString(" "+willColoredText);
        phone.setSpan(new ForegroundColorSpan(greenColor), 0 ,phone.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(textRes);
        textView.append(phone);
    }

    private void enabledButton() {
        mBtnEnter.setEnabled(true);
        mBtnEnter.setBackground(mDrawableOnButtonEnabled);
        mBtnEnter.setTextColor(mColorOnButtonEnabled);
    }

    private void disabledButton() {
        mBtnEnter.setEnabled(false);
        mBtnEnter.setBackground(mDrawableOnButtonDisabled);
        mBtnEnter.setTextColor(mColorOnButtonDisabled);
    }
}
