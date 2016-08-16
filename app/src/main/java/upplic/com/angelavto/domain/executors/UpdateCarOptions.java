package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.repositories.Repository;

public class UpdateCarOptions implements Interactor1<CarOptions, CarOptions> {

    private Repository mRepository;

    @Inject
    public UpdateCarOptions(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<CarOptions> execute(CarOptions data) {
        return mRepository.updateCarOptions(data);
    }
}