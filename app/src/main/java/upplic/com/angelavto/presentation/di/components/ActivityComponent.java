package upplic.com.angelavto.presentation.di.components;

import dagger.Component;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.di.scopes.PerActivity;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcMainCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcAvtoCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtCreateCarCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtShopCtrl;


@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(AcMainCtrl viewController);
    void inject(FmtShopCtrl viewController);
    void inject(FmtCreateCarCtrl viewController);
    void inject(AcAvtoCtrl viewController);
    void inject(AcLoginCtrl viewController);
}
