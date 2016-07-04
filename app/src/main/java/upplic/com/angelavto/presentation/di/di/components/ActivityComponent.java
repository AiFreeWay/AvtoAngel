package upplic.com.angelavto.presentation.di.di.components;


import dagger.Component;
import upplic.com.angelavto.presentation.di.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.di.di.scopes.PerActivity;

@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
}
