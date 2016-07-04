package upplic.com.angelavto.presentation.di.components;


import dagger.Component;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.di.scopes.PerActivity;


@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
}
