package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;

import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;
import upplic.com.angelavto.presentation.views.fragments.GetCodeFragment;


public class FmtGetCodeCtrl extends ViewController<GetCodeFragment> {

    public FmtGetCodeCtrl(GetCodeFragment view) {
        super(view);
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
}
