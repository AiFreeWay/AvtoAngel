package upplic.com.angelavto.presentation.di.modules;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.domain.executors.AlarmInteractorImpl;
import upplic.com.angelavto.domain.executors.GetCarsOptions;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.CarOptions;

@Module
public class ServiceModule {

    public static final String GET_CAR_OPTIONS = "getcarsoptionsservice";
    public static final String ALARM = "alarminteractorservice";

    @Provides
    @Named(GET_CAR_OPTIONS)
    public Interactor0<List<CarOptions>> provideGetCarsOptions(GetCarsOptions getCarsOptions) {
        return getCarsOptions;
    }

    @Provides
    @Named(ALARM)
    public AlarmInteractor provideAlarmInteractor(AlarmInteractorImpl alarmInteractor) {
        return alarmInteractor;
    }
}
