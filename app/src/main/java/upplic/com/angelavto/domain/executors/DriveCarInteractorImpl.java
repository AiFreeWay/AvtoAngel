package upplic.com.angelavto.domain.executors;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.DriveCarInteractor;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.domain.repositories.Repository;

public class DriveCarInteractorImpl implements DriveCarInteractor {

    private Repository mRepository;

    @Inject
    public DriveCarInteractorImpl(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<Integer> deleteAllCarOptions() {
        return mRepository.deleteAllCarOptions();
    }

    @Override
    public Observable<List<CarOptions>> getCarsOptions() {
        return mRepository.getCarsOptions();
    }

    @Override
    public Observable<CarOptions> updateCarOptions(CarOptions data) {
        return mRepository.updateCarOptions(data);
    }

    @Override
    public Observable<Status> setStatus(Status data) {
        return mRepository.setStatus(data);
    }
}
