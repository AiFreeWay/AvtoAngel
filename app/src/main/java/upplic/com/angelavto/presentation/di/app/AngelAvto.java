package upplic.com.angelavto.presentation.di.app;


import android.app.Application;

import upplic.com.angelavto.presentation.di.di.components.ApplicationComponent;
import upplic.com.angelavto.presentation.di.di.components.DaggerApplicationComponent;
import upplic.com.angelavto.presentation.di.di.modules.ApplicationModule;

public class AngelAvto extends Application {

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
