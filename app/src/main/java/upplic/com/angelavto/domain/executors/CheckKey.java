package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.repositories.Repository;

public class CheckKey implements Interactor0<Boolean> {

    private Repository mRepository;

    @Inject
    public CheckKey(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Boolean> execute() {
        return mRepository.checkKey();
    }
}
