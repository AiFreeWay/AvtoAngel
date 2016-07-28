package upplic.com.angelavto.presentation.view_controllers;


import javax.inject.Inject;

import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.views.activities.
        SelectBeaconActivity;

public class AcSelectBeaconCtrl extends ViewController<SelectBeaconActivity> {

    @Inject
    FragmentRouter.RouterBilder mRouterBilder;
    @Inject
    FragmentsFactory mFragmentsFactory;

    private FragmentRouter mRouter;

    public AcSelectBeaconCtrl(SelectBeaconActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mRootView.getFragmentsBodyResId());
    }

    @Override
    public void start() {
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.SELECT_BEACON));
    }
}
