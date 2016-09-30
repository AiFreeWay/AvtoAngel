package upplic.com.angelavto.presentation.view_controllers;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import upplic.com.angelavto.presentation.factories.LoginViewPagerFactory;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
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
        Intent intent;
        if (Hawk.contains(LoginActivity.FIRTS_START)) {
            intent = getStartActivityIntent(MainActivity.class);
            if (mRootView.getAlarm() != null)
                intent.putExtra(MainActivity.ALARM_TAG, mRootView.getAlarm());
            mRootView.startActivity(intent);
        } else if (Hawk.contains(LoginActivity.API_KEY_TAG)) {
            intent = getStartActivityIntent(SelectBeaconActivity.class);
            mRootView.startActivity(intent);
        }
    }

    private Intent getStartActivityIntent(Class<? extends Activity> activityClass) {
        return new Intent(mRootView, activityClass)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
