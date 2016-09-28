package upplic.com.angelavto.presentation.app;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

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
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(this))
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
