package upplic.com.angelavto.presentation.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.domain.executors.AlarmInteractorImpl;
import upplic.com.angelavto.domain.executors.AuthInteractorImpl;
import upplic.com.angelavto.domain.executors.BeaconsInteractorImpl;
import upplic.com.angelavto.domain.executors.CarsInteractorImpl;
import upplic.com.angelavto.domain.executors.DriveCarInteractorImpl;
import upplic.com.angelavto.domain.executors.MapInteractorImpl;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.AuthInteractor;
import upplic.com.angelavto.domain.interactors.BeaconsInteractor;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.domain.interactors.DriveCarInteractor;
import upplic.com.angelavto.domain.interactors.MapInteractor;
import upplic.com.angelavto.presentation.factories.AppMenuFactory;
import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.factories.LoginViewPagerFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;


@Module
public class ActivityModule {

    public static final String ALARM = "alarminteractor";
    public static final String AUTH = "authinteractor";
    public static final String CARS = "carsinteractor";
    public static final String DRIVE_CAR = "drivecarinteractor";
    public static final String MAP = "mapinteractor";
    public static final String BEACONS = "beaconsinteractor";

    private FragmentRouter.RouterBilder mRouterBilder;
    private FragmentsFactory mFragmentsFactory;
    private AppMenuFactory mAppMenuFactory;
    private AvtoViewPagerFactory.Builder mAvtoViewPagerFactoryBuilder;
    private LoginViewPagerFactory mLoginViewPagerFactory;

    public ActivityModule(BaseActivity activity) {
        mRouterBilder = new FragmentRouter.RouterBilder();
        mFragmentsFactory = new FragmentsFactory();
        mAppMenuFactory = new AppMenuFactory(activity);
        mAvtoViewPagerFactoryBuilder = new AvtoViewPagerFactory.Builder();
        mLoginViewPagerFactory = new LoginViewPagerFactory();
    }

    @Provides
    public FragmentRouter.RouterBilder provideRouterBilder() {
        return mRouterBilder;
    }

    @Provides
    public FragmentsFactory provideFragmentsFactory() {
        return mFragmentsFactory;
    }

    @Provides
    public AppMenuFactory provideAppMenuFactory() {
        return mAppMenuFactory;
    }

    @Provides
    public AvtoViewPagerFactory.Builder provideAvtoViewPagerFactoryBuilder() {
        return mAvtoViewPagerFactoryBuilder;
    }

    @Provides
    public LoginViewPagerFactory provideLoginViewPagerFactory() {
        return mLoginViewPagerFactory;
    }


    @Provides
    @Named(ALARM)
    public AlarmInteractor provideAlarmInteractor(AlarmInteractorImpl alarmInteractor) {
        return alarmInteractor;
    }

    @Provides
    @Named(AUTH)
    public AuthInteractor provideAuthInteractor(AuthInteractorImpl authInteractor) {
        return authInteractor;
    }

    @Provides
    @Named(CARS)
    public CarsInteractor provideCarsInteractor(CarsInteractorImpl carsInteractor) {
        return carsInteractor;
    }

    @Provides
    @Named(DRIVE_CAR)
    public DriveCarInteractor provideDriveCarInteractor(DriveCarInteractorImpl driveCarInteractor) {
        return driveCarInteractor;
    }

    @Provides
    @Named(MAP)
    public MapInteractor providMapInteractor(MapInteractorImpl mapInteractor) {
        return mapInteractor;
    }

    @Provides
    @Named(BEACONS)
    public BeaconsInteractor provideBeaconsInteractor(BeaconsInteractorImpl beaconsInteractor) {
        return beaconsInteractor;
    }
}
