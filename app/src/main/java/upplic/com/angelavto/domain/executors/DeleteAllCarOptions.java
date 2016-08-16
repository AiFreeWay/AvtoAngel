package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.repositories.Repository;

public class DeleteAllCarOptions implements Interactor0 {

    private Repository mRepository;

    @Inject
    public DeleteAllCarOptions(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<Void> execute() {
        Observable.OnSubscribe<Void> subscribe = oberver -> {
            try {
                mRepository.deleteAllCarOptions();
            } catch (Exception e) {
                oberver.onError(e);
            }
            oberver.onCompleted();
        };
        return Observable.create(subscribe);
    }
}
