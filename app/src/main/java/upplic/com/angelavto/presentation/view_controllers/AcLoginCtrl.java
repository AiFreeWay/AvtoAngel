package upplic.com.angelavto.presentation.view_controllers;

import javax.inject.Inject;

import upplic.com.angelavto.presentation.factories.LoginViewPagerFactory;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


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

    }
}
