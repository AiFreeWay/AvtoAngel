package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;

import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.views.activities.MainActivity;

public class AcLoginCtrl extends ViewController<LoginActivity> {


    public AcLoginCtrl(LoginActivity view) {
        super(view);
    }

    @Override
    public void start() {

    }

    public void startMainActivity() {
        if (mRootView.getCode().isEmpty())
            mRootView.showNeutralDialog(R.string.need_input_code);
        else {
            Intent intent = new Intent(mRootView, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mRootView.startActivity(intent);
        }
    }
}
