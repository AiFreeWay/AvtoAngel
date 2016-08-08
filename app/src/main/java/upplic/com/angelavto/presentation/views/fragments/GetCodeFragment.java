package upplic.com.angelavto.presentation.views.fragments;


import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.rey.material.widget.ProgressView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.presentation.view_controllers.FmtGetCodeCtrl;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class GetCodeFragment extends BaseFragment<FmtGetCodeCtrl> {

    private final int INTERVAL_LENGTH = 45;
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
    @BindView(R.id.fmt_get_code_pv_progress)
    ProgressView mProgress;

    private LoginActivity mActivity;
    private Drawable mDrawableOnButtonEnterEnabled;
    private Drawable mDrawableOnButtonEnterDisabled;
    private int mColorGrideperlevy;
    private int mColorSilverGrey;
    private int mColorMarengo;
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
        mViewController = new FmtGetCodeCtrl(this);

        mDrawableOnButtonEnterEnabled = ContextCompat.getDrawable(getContext(), R.drawable.selector_green_button);
        mDrawableOnButtonEnterDisabled = ContextCompat.getDrawable(getContext(), R.drawable.button_green_disabled);
        mColorGrideperlevy = ContextCompat.getColor(getContext(), R.color.grideperlevy);
        mColorSilverGrey = ContextCompat.getColor(getContext(), R.color.silver_gray);
        mColorMarengo = ContextCompat.getColor(getContext(), R.color.marengo);

        CALENDAR.setTimeInMillis(System.currentTimeMillis());
        mBtnEnter.setOnClickListener(v -> mViewController.login());
        mBtnGetCode.setOnClickListener(v -> mViewController.registration());
    }

    @Override
    public void onResume() {
        super.onResume();
        disabledButtonEnter();
        mEtCode.setEnabled(false);
        mEtCode.setText("");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mTimerSubscription != null)
            mTimerSubscription.unsubscribe();
    }

    public void doOnSendCode() {
        Toast.makeText(getContext(), R.string.input_code_message, Toast.LENGTH_SHORT).show();
        mTimerSubscription = mTimer
                .doOnUnsubscribe(() -> {
                    enabledButtonGetCode();
                    mProgress.stop();
                    mTvDescription.setText("");
                    mTvPepeatedResponse.setText("");})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangeInterval);
        appendColorText(R.string.fmt_get_code_description, mActivity.getNubmer(), mTvDescription);
        disabledButtonGetCode();
        enabledButtonEnter();
        mEtCode.setEnabled(true);
        mProgress.start();
    }

    public void showToast(int stringRes) {
        Toast.makeText(getContext(), stringRes, Toast.LENGTH_SHORT).show();
    }

    public RegistrationDomain getRegistrationModel() {
        return new RegistrationDomain(mActivity.getNubmer());
    }

    public LoginDomain getLoginModel() {
        return new LoginDomain(mActivity.getNubmer(), mEtCode.getText().toString());
    }

    private void onChangeInterval(Long time) {
        if (time == INTERVAL_LENGTH)
            mTimerSubscription.unsubscribe();
        else {
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

    private void enabledButtonEnter() {
        mBtnEnter.setEnabled(true);
        mBtnEnter.setBackground(mDrawableOnButtonEnterEnabled);
        mBtnEnter.setTextColor(mColorGrideperlevy);
    }

    private void disabledButtonEnter() {
        mBtnEnter.setEnabled(false);
        mBtnEnter.setBackground(mDrawableOnButtonEnterDisabled);
        mBtnEnter.setTextColor(mColorSilverGrey);
    }

    private void enabledButtonGetCode() {
        mBtnGetCode.setEnabled(true);
        mBtnGetCode.setTextColor(mColorGrideperlevy);
    }

    private void disabledButtonGetCode() {
        mBtnGetCode.setEnabled(false);
        mBtnGetCode.setTextColor(mColorMarengo);
    }
}
