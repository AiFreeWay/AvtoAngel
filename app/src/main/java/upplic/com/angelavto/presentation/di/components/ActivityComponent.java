package upplic.com.angelavto.presentation.di.components;

import dagger.Component;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.di.scopes.PerActivity;
import upplic.com.angelavto.presentation.factories.AppMenuFactory;
import upplic.com.angelavto.presentation.view_controllers.AcEditAvtoCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcMainCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoCtrl;
import upplic.com.angelavto.presentation.view_controllers.AcSelectBeaconCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoDriveCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtBeaconsShopCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtCreateCarCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtGetCodeCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtMapCtrl;
import upplic.com.angelavto.presentation.view_controllers.FmtSelectBeaconController;


@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(AcMainCtrl viewController);
    void inject(FmtCreateCarCtrl viewController);
    void inject(FmtAvtoCtrl viewController);
    void inject(AcLoginCtrl viewController);
    void inject(AcSelectBeaconCtrl viewController);
    void inject(FmtSelectBeaconController viewController);
    void inject(FmtBeaconsShopCtrl viewController);
    void inject(AcEditAvtoCtrl viewController);
    void inject(FmtGetCodeCtrl viewController);
    void inject(FmtAvtoDriveCtrl viewController);
    void inject(FmtMapCtrl viewController);
}
