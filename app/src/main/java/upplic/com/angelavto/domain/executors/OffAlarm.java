package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.repositories.Repository;

public class OffAlarm implements Interactor0<String> {

    private Repository mRepository;

    @Inject
    public OffAlarm(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<String> execute() {
        return mRepository.offAlarm();
    }
}
