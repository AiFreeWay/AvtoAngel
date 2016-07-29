package upplic.com.angelavto.presentation.view_controllers;

import javax.inject.Inject;

import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.views.activities.SelectBeaconActivity;
import upplic.com.angelavto.presentation.views.fragments.SelectBeaconFragment;


public class FmtSelectBeaconController extends ViewController<SelectBeaconFragment> {

    @Inject
    FragmentRouter.RouterBilder mRouterBilder;
    @Inject
    FragmentsFactory mFragmentsFactory;

    private FragmentRouter mRouter;
    private SelectBeaconActivity mAcitity;

    public FmtSelectBeaconController(SelectBeaconFragment view) {
        super(view);
        mAcitity = (SelectBeaconActivity) mRootView.getBaseActivity();
        mRootView.getActivityComponent()
                .inject(this);
        mRouter = mRouterBilder.getRouter(mAcitity.getFragmentsBodyResId());
    }

    @Override
    public void start() {

    }

    public void openToBeaconsFragment() {
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.BEACONS));
    }
}
