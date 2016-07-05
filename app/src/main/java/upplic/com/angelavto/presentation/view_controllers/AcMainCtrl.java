package upplic.com.angelavto.presentation.view_controllers;


import javax.inject.Inject;

import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.utils.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.MainActivity;

public class AcMainCtrl extends ViewController<MainActivity> {

    @Inject
    FragmentRouter.RouterBilder mRouterBilder;
    @Inject
    FragmentsFactory mFragmentsFactory;

    private FragmentRouter mRouter;

    public AcMainCtrl(MainActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mRootView.getFragmentsBodyResId());
    }

    @Override
    public void start() {
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.SHOP));
    }

    public void popBack() {
        if (!mRouter.back())
            mRootView.finish();
    }
}
