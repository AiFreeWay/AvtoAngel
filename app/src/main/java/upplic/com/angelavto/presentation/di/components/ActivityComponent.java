package upplic.com.angelavto.presentation.di.components;

import dagger.Component;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.di.scopes.PerActivity;
import upplic.com.angelavto.presentation.view_controllers.AcEditAvtoCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcMainCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcAvtoCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcSelectBeaconCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtBeaconsCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtBeaconsShopCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtCreateCarCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtGarageCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtSelectBeaconController;


@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(AcMainCtrl viewController);
    void inject(FmtBeaconsCtrl viewController);
    void inject(FmtCreateCarCtrl viewController);
    void inject(AcAvtoCtrl viewController);
    void inject(AcLoginCtrl viewController);
    void inject(AcSelectBeaconCtrl viewController);
    void inject(FmtSelectBeaconController viewController);
    void inject(FmtBeaconsShopCtrl viewController);
    void inject(FmtGarageCtrl viewController);
    void inject(AcEditAvtoCtrl viewController);
}
