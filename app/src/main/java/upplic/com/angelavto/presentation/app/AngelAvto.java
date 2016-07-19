package upplic.com.angelavto.presentation.app;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import upplic.com.angelavto.presentation.di.components.ApplicationComponent;
import upplic.com.angelavto.presentation.di.components.DaggerApplicationComponent;
import upplic.com.angelavto.presentation.di.modules.ApplicationModule;


public class AngelAvto extends Application {

    public static final String UNIVERSAL_ERROR_TAG = "++++";

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
