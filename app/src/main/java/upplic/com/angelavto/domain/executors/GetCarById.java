package upplic.com.angelavto.domain.executors;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;


public class GetCarById implements Interactor1<Car, Integer> {

    private Repository mRepository;

    @Inject
    public GetCarById(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Car> execute(Integer data) {
        Observable.OnSubscribe<Car> subscriber = observer -> {
            try {
                observer.onNext(mRepository.getCarByIdNetwork(data));
            } catch (Exception e) {
                observer.onError(e);
            }
            observer.onCompleted();
        };
        return Observable.create(subscriber);
    }
}
