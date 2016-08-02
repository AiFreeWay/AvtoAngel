package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;

public class CreateCar implements Interactor<Car> {

    private Repository mRepository;

    @Inject
    public CreateCar(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<Void> execute(Car data) {
        Observable.OnSubscribe<Void> subscriber = observer -> {
            try {
                mRepository.createCar(data);
            } catch (Exception e) {
                observer.onError(e);
            }
            observer.onCompleted();
        };
        return Observable.create(subscriber);
    }
}
