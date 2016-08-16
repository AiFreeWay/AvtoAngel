package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.di.components.DaggerActivityComponent;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;

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

    public void refresh() {

    }

    public VC getViewController() {
        return mViewController;
    }
}
