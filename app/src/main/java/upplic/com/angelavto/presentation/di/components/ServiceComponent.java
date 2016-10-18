package upplic.com.angelavto.presentation.di.components;

import dagger.Component;
import upplic.com.angelavto.presentation.di.modules.ServiceModule;
import upplic.com.angelavto.presentation.di.scopes.PerService;
import upplic.com.angelavto.presentation.services.PushNotificationReceiver;


@PerService
@Component(modules = ServiceModule.class, dependencies = ApplicationComponent.class)
public interface ServiceComponent {

    void inject(PushNotificationReceiver service);
}
