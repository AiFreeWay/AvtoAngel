package upplic.com.angelavto.domain.executors;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.repositories.Repository;


public class GetBeacons implements Interactor0<List<Beacon>> {

    private Repository mRepository;

    @Inject
    public GetBeacons(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<List<Beacon>> execute() {
        return mRepository.getBeacons().cache();
    }
}
