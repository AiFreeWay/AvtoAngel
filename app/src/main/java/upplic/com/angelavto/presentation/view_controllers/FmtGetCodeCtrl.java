package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.executors.Login;
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
        mRegistration.execute(mRootView.getRegistrationModel())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registrationResult -> mRootView.doOnSendCode(),
                    e -> { mRootView.showToast(R.string.cannot_send_code);
                        Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGetCodeCtrl: registration error "+e.toString());});
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
            .subscribe();
        Intent intent = new Intent(mRootView.getBaseActivity(), SelectBeaconActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mRootView.startActivity(intent);
    }

    private boolean isValidCode(LoginDomain loginDomain) {
        String code = loginDomain.getCode();
        return !code.isEmpty() && code.length() == 4;
    }
}
