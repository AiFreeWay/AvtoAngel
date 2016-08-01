package upplic.com.angelavto.presentation.view_controllers;

import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.views.activities.AvtoActivity;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;


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
