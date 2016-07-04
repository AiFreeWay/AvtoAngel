package upplic.com.angelavto.presentation.di.view_controllers;


import android.content.Intent;

import upplic.com.angelavto.presentation.di.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.di.views.activities.MainActivity;

public class AcLoginCtrl extends ViewController<LoginActivity> {


    public AcLoginCtrl(LoginActivity view) {
        super(view);
    }

    @Override
    public void start() {

    }

    public void startMainActivity() {
        Intent intent = new Intent(mRootView, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mRootView.startActivity(intent);
    }
}
