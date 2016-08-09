package upplic.com.angelavto.domain.executors;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;

public class GetCars implements Interactor0<List<Car>> {

    private Repository mRepository;

    @Inject
    public GetCars(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<List<Car>> execute() {
        return mRepository.getCarsNetwork()
                .cache();
    }
}
