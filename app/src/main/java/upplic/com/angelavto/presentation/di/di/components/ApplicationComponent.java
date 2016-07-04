package upplic.com.angelavto.presentation.di.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import upplic.com.angelavto.presentation.di.di.modules.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context provideContext();
}
