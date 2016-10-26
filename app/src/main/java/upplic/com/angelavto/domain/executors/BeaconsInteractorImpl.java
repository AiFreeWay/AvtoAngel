package upplic.com.angelavto.domain.executors;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.BeaconsInteractor;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.repositories.Repository;


public class BeaconsInteractorImpl implements BeaconsInteractor {

    private Repository mRepository;

    @Inject
    public BeaconsInteractorImpl(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<List<Beacon>> getBeacons() {
        return mRepository.getBeacons()
                .cache();
    }
}
