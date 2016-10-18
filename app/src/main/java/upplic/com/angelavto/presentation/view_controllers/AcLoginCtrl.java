package upplic.com.angelavto.presentation.view_controllers;

import android.app.Activity;
import android.content.Intent;
import upplic.com.angelavto.presentation.views.fragments.GetCodeFragment;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import upplic.com.angelavto.presentation.factories.LoginViewPagerFactory;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;


public class AcLoginCtrl extends ViewController<LoginActivity> {

    private static final int GET_CODE_FRAGMENT_INDEX = 1;

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
        if (Hawk.contains(LoginActivity.FIRTS_START)) {
            Intent intent = getStartActivityIntent(MainActivity.class);
            mRootView.startActivity(intent);
        } else if (Hawk.contains(LoginActivity.API_KEY_TAG)) {
            Intent intent = getStartActivityIntent(SelectBeaconActivity.class);
            mRootView.startActivity(intent);
        }
    }

    public void setCode(String code) {
        GetCodeFragment  getCodeFragment = (GetCodeFragment) mFactory.getFragments().get(GET_CODE_FRAGMENT_INDEX);
        getCodeFragment.setCode(code);
    }

    public void reloadGetCodeFragment() {
        GetCodeFragment getCodeFragment = (GetCodeFragment) mFactory.getFragments().get(GET_CODE_FRAGMENT_INDEX);
        getCodeFragment.reload();
    }

    private Intent getStartActivityIntent(Class<? extends Activity> activityClass) {
        return new Intent(mRootView, activityClass)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
