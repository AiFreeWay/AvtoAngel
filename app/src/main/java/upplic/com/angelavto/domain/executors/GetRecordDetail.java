package upplic.com.angelavto.domain.executors;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.repositories.Repository;

public class GetRecordDetail implements Interactor1<Record, Integer> {

    private Repository mRepository;

    @Inject
    public GetRecordDetail(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Record> execute(Integer data) {
        return mRepository.getRecordDetail(data)
                .cache();
    }
}