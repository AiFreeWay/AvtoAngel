package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;

import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;
import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;

public class FmtAvtoDriveCtrl extends ViewController<AvtoDriveFragment> {

    public FmtAvtoDriveCtrl(AvtoDriveFragment view) {
        super(view);
    }

    @Override
    public void start() {

    }

    public void openEditAvtoActivity() {
        Intent intent = new Intent(getRootView().getContext(), EditAvtoActivity.class);
        intent.putExtra(EditAvtoActivity.CAR_TAG, mRootView.getCar());
        mRootView.startActivity(intent);
    }
}
