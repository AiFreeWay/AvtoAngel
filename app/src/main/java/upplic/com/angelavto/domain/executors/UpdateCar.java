package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;

public class UpdateCar implements Interactor<Car> {

    private Repository mRepository;

    @Inject
    public UpdateCar(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable execute(Car data) {
        return mRepository.updateCar(data);
    }
}
