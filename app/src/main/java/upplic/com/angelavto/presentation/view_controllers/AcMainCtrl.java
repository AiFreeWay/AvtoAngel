package upplic.com.angelavto.presentation.view_controllers;


import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Inject;

import upplic.com.angelavto.presentation.utils.AppMenuFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.utils.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.MainActivity;

public class AcMainCtrl extends ViewController<MainActivity> {

    @Inject
    FragmentRouter.RouterBilder mRouterBilder;
    @Inject
    FragmentsFactory mFragmentsFactory;
    @Inject
    AppMenuFactory mAppMenuFactory;

    private FragmentRouter mRouter;
    private LayoutInflater mLayoutInflater;

    public AcMainCtrl(MainActivity view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mRootView.getFragmentsBodyResId());
    }

    @Override
    public void start() {
        mRootView.loadData(mAppMenuFactory.getMenu());
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.SHOP));
    }

    public void popBack() {
        if (!mRouter.back())
            mRootView.finish();
    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }
}
