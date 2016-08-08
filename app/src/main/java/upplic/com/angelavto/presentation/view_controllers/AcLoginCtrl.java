package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import upplic.com.angelavto.presentation.factories.LoginViewPagerFactory;
import upplic.com.angelavto.presentation.views.activities.AvtoActivity;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;


public class AcLoginCtrl extends ViewController<LoginActivity> {

    @Inject
    LoginViewPagerFactory mFactory;

    public AcLoginCtrl(LoginActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mRootView.loadData(mFactory.getFragments());
    }

    @Override
    public void start() {
        if (Hawk.contains(LoginActivity.API_KEY_TAG))
            startBeackonsActivity();
    }

    private void startBeackonsActivity() {
        Intent intent = new Intent(mRootView, SelectBeaconActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mRootView.startActivity(intent);
    }
}
