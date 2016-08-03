package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;

public class CreateCar implements Interactor1<Boolean, Car> {

    private Repository mRepository;

    @Inject
    public CreateCar(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<Boolean> execute(Car data) {
        boolean canCreateCar = mRepository.canCreateCar(data);
        if (canCreateCar)
            return mRepository.createCar(data)
                    .flatMap(carTableEntity -> Observable.just(canCreateCar));
        else
            return Observable.just(canCreateCar);
    }
}
