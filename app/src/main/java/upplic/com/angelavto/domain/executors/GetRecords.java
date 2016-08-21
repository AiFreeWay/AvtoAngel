package upplic.com.angelavto.domain.executors;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.repositories.Repository;

public class GetRecords implements Interactor1<List<Record>, Integer> {

    private Repository mRepository;

    @Inject
    public GetRecords(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<List<Record>> execute(Integer data) {
        return mRepository.getRecords(data)
                .cache();
    }
}
