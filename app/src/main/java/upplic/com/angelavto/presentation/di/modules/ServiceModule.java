package upplic.com.angelavto.presentation.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.domain.executors.AlarmInteractorImpl;
import upplic.com.angelavto.domain.executors.DriveCarInteractorImpl;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.DriveCarInteractor;

@Module
public class ServiceModule {

    public static final String GET_CAR_OPTIONS = "getcarsoptionsservice";
    public static final String ALARM = "alarminteractorservice";
    public static final String DRIVE_CAR = "drivecarinteractorservice";

    @Provides
    @Named(ALARM)
    public AlarmInteractor provideAlarmInteractor(AlarmInteractorImpl alarmInteractor) {
        return alarmInteractor;
    }

    @Provides
    @Named(DRIVE_CAR)
    public DriveCarInteractor provideDriveCarInteractor(DriveCarInteractorImpl driveCarInteractor) {
        return driveCarInteractor;
    }
}
