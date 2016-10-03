package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;
import upplic.com.angelavto.presentation.views.fragments.GetCodeFragment;


public class FmtGetCodeCtrl extends ViewController<GetCodeFragment> {

    private static final String SMS_COUNT_TAG = "smscount";
    private static final String LAST_SMS_DATE_TAG = "lastsmsdate";
    private static final int MAX_SMS_COUNT = 5;

    private final Calendar CALENDAR = new GregorianCalendar();

    @Inject @Named(ActivityModule.REGISTRATION)
    Interactor1<RegistrationResult, RegistrationDomain> mRegistration;
    @Inject @Named(ActivityModule.LOGIN)
    Interactor1<LoginResult, LoginDomain> mLogin;

    public FmtGetCodeCtrl(GetCodeFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }

    public void registration() {
        if (writeSmsLog())
            mRegistration.execute(mRootView.getRegistrationModel())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(registrationResult -> mRootView.doOnSendCode(),
                        e -> { mRootView.showToast(R.string.cannot_send_code);
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGetCodeCtrl: registration error "+e.toString());});
    }

    public boolean writeSmsLog() {
        if (Hawk.contains(SMS_COUNT_TAG)) {
            int smsCount = Hawk.get(SMS_COUNT_TAG);
            if (smsCount < MAX_SMS_COUNT)
                writeSmsToPreferense(++smsCount);
            else {
                if (isSmsDateLimitActs()) {
                    mRootView.showToast(R.string.sms_limit);
                    return false;
                } else
                    removeSmsLog();
            }
        } else
            writeSmsToPreferense(1);
        return true;
    }

    public void login() {
        LoginDomain loginDomain = mRootView.getLoginModel();
        if (isValidCode(loginDomain)) {
            mLogin.execute(loginDomain)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::endLogin,
                            e -> {mRootView.showToast(R.string.error_autorization);
                                Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGetCodeCtrl: login error " + e.toString());});
        } else
            mRootView.showToast(R.string.code_must_be_correct);
    }

    private void endLogin(LoginResult loginResult) {
        Hawk.putObservable(LoginActivity.API_KEY_TAG, loginResult.getKey())
            .subscribeOn(Schedulers.newThread())
            .subscribe(aBoolean -> startActivity(),
                    e -> { startActivity();
                        Log.d(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGetCodeCtrl endLogin error: "+e.toString());});
    }

    private void startActivity() {
        Intent intent = new Intent(mRootView.getBaseActivity(), SelectBeaconActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mRootView.startActivity(intent);
    }

    private boolean isValidCode(LoginDomain loginDomain) {
        String code = loginDomain.getCode();
        return !code.isEmpty() && code.length() == 4;
    }

    private void writeSmsToPreferense(int count) {
        Hawk.put(LAST_SMS_DATE_TAG, System.currentTimeMillis());
        Hawk.put(SMS_COUNT_TAG, count);
    }

    private void removeSmsLog() {
        Hawk.remove(LAST_SMS_DATE_TAG);
        Hawk.remove(SMS_COUNT_TAG);
    }

    private boolean isSmsDateLimitActs() {
        CALENDAR.setTimeInMillis(Hawk.get(LAST_SMS_DATE_TAG));
        int lastSmsDay = CALENDAR.get(Calendar.DAY_OF_YEAR);
        CALENDAR.setTimeInMillis(System.currentTimeMillis());
        int currentDay = CALENDAR.get(Calendar.DAY_OF_YEAR);
        return lastSmsDay == currentDay;
    }
}
