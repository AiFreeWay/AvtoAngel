package upplic.com.angelavto.presentation.view_controllers;


import javax.inject.Inject;
import javax.inject.Named;

import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.views.activities.
        SelectBeaconActivity;

public class AcSelectBeaconCtrl extends ViewController<SelectBeaconActivity> {

    @Inject @Named(ActivityModule.DELETE_ALL_CAR_OPTIONS)
    Interactor0 mDeleteAllCarOptions;
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
        mDeleteAllCarOptions.execute()
                .subscribeOn(Schedulers.newThread())
                .subscribe();
        mRouter.show(mFragmentsFactory.getFragment(FragmentsFactory.Fragments.SELECT_BEACON));
    }

    public void popBack() {
        if (!mRouter.back())
            mRootView.finish();
    }
}
