package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.di.components.DaggerActivityComponent;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;

public abstract class BaseActivity<VC> extends AppCompatActivity {

    protected VC mViewController;
    private ActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        mComponent = DaggerActivityComponent.builder()
                .applicationComponent(getAngelAvtoApplication().getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public AngelAvto getAngelAvtoApplication() {
        return (AngelAvto) getApplication();
    }

    public ActivityComponent getActivityComponent() {
        return mComponent;
    }

    public void refresh() {

    }

    public VC getViewController() {
        return mViewController;
    }
}
