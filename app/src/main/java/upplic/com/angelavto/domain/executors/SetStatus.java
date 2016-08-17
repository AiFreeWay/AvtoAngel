package upplic.com.angelavto.domain.executors;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.domain.repositories.Repository;

public class SetStatus implements Interactor1<Status, Status> {

    private Repository mRepository;

    @Inject
    public SetStatus(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Status> execute(Status data) {
        return mRepository.setStatus(data);
    }
}