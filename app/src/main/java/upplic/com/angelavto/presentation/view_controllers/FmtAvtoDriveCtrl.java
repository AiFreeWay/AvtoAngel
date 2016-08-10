package upplic.com.angelavto.presentation.view_controllers;


import android.content.Intent;

import upplic.com.angelavto.domain.models.Car;
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
        Car car = mRootView.getCar();
        if (car != null) {
            Intent intent = new Intent(getRootView().getContext(), EditAvtoActivity.class);
            intent.putExtra(EditAvtoActivity.CAR_TAG, car);
            mRootView.startActivity(intent);
        }
    }
}
