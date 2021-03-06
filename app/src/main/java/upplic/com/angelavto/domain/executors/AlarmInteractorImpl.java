package upplic.com.angelavto.domain.executors;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.models.Alarm;
import upplic.com.angelavto.domain.repositories.Repository;


public class AlarmInteractorImpl implements AlarmInteractor {

    private Repository mRepository;

    @Inject
    public AlarmInteractorImpl(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<Integer> deleteAllAlarms() {
        return mRepository.deleteAllAlarms();
    }

    @Override
    public Observable<Integer> deleteAlarmById(int id) {
        return mRepository.deleteAlarmByCarId(id);
    }

    @Override
    public Observable<Integer> insertAlarm(Alarm alarm) {
        return mRepository.insertAlarm(alarm)
                .map(insertId -> {mRepository.updateCarOptionsEditTime(insertId);
                    return insertId;});
    }

    @Override
    public Observable<Alarm> getAlarmByCarId(int id) {
        return mRepository.getAlarmByCarId(id);
    }

    @Override
    public Observable<List<Alarm>> getAlarmsFromDB() {
        return mRepository.getAlarms();
    }

    @Override
    public Observable<List<Alarm>> getAlarmsFromNetwork() {
        return mRepository.checkAlarm();
    }

    @Override
    public Observable<Integer> putAlarms(List<Alarm> alarms) {
        return mRepository.putAlarms(alarms);
    }

    @Override
    public Observable<String> offAlarm() {
        return mRepository.offAlarm();
    }
}
