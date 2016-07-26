package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;

import upplic.com.angelavto.presentation.views.activities.MainActivity;
import upplic.com.angelavto.presentation.views.fragments.GetCodeFragment;


public class FmtGetCodeCtrl extends ViewController<GetCodeFragment> {

    public FmtGetCodeCtrl(GetCodeFragment view) {
        super(view);
    }

    @Override
    public void start() {

    }

    public void startMainActivity() {
        Intent intent = new Intent(mRootView.getBaseActivity(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mRootView.startActivity(intent);
    }
}
