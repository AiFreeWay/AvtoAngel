package upplic.com.angelavto.domain.executors;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Alarm;
import upplic.com.angelavto.domain.repositories.Repository;

public class CheckAlarm implements Interactor0<List<Alarm>> {

    private Repository mRepository;

    @Inject
    public CheckAlarm(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<List<Alarm>> execute() {
        return mRepository.checkAlarm();
    }
}
