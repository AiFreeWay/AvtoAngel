package upplic.com.angelavto.presentation.di.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import upplic.com.angelavto.presentation.di.app.AngelAvto;
import upplic.com.angelavto.presentation.di.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.di.di.components.DaggerActivityComponent;
import upplic.com.angelavto.presentation.di.di.modules.ActivityModule;

public abstract class BaseActivity<VC> extends AppCompatActivity {

    protected VC mViewController;
    private ActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public VC getViewController() {
        return mViewController;
    }
}
