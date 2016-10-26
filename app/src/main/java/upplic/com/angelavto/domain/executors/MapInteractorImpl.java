package upplic.com.angelavto.domain.executors;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.MapInteractor;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.repositories.Repository;


public class MapInteractorImpl implements MapInteractor {

    private Repository mRepository;

    @Inject
    public MapInteractorImpl(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<Record> getRecordDetail(Integer recordId) {
        return mRepository.getRecordDetail(recordId)
                .cache();
    }

    @Override
    public Observable<List<Record>> getRecords(Integer carId) {
        return mRepository.getRecords(carId)
                .cache();
    }
}
