package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Login;
import upplic.com.angelavto.domain.models.SendCodeRequestResult;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;
import upplic.com.angelavto.presentation.views.fragments.GetCodeFragment;


public class FmtGetCodeCtrl extends ViewController<GetCodeFragment> {

    @Inject
    @Named(ActivityModule.REGISTRATION)
    Interactor1<SendCodeRequestResult, Login> mRegistration;

    public FmtGetCodeCtrl(GetCodeFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }

    public void startSelectBeaconActivity() {
        Intent intent = new Intent(mRootView.getBaseActivity(), SelectBeaconActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mRootView.startActivity(intent);
    }

    public void sendCode() {
        mRegistration.execute(mRootView.getLogin())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sendCodeResultRequest -> {
                    if (sendCodeResultRequest.isSuccess())
                        mRootView.doOnSendCode();
                    else
                        mRootView.showWarning();},
                    e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGetCodeCtrl: sendCode error "+e.toString()));
    }
}
