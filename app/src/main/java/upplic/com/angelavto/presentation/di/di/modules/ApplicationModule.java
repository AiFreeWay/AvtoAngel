package upplic.com.angelavto.presentation.di.di.modules;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.presentation.di.app.AngelAvto;

@Module
public class ApplicationModule {

    private AngelAvto mApplication;

    public ApplicationModule(AngelAvto angelAvto) {
        mApplication = angelAvto;
    }

    @Provides
    public Context provideContext() {
        return mApplication;
    }
}
