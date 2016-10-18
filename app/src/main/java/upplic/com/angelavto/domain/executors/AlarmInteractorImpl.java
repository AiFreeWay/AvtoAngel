package upplic.com.angelavto.domain.executors;

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
}
