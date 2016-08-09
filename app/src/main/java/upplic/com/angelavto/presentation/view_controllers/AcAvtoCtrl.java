package upplic.com.angelavto.presentation.view_controllers;

import javax.inject.Inject;

import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.views.activities.AvtoActivity;


public class AcAvtoCtrl extends ViewController<AvtoActivity> {

    @Inject
    AvtoViewPagerFactory.Builder mFactoryBuilder;

    private AvtoViewPagerFactory mFactory;

    public AcAvtoCtrl(AvtoActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
        mFactory = mFactoryBuilder.build(mRootView.getTlTabs());
        mRootView.loadData(mFactory.getTabs(), mFactory.getFragments());

    }

    @Override
    public void start() {

    }
}
