package upplic.com.angelavto.domain.executors;


import android.util.Log;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.domain.repositories.Repository;

public class CreateCar implements Interactor1<UpsertCarResult, Car> {

    private final Car MOCK_CAR;

    private Repository mRepository;

    @Inject
    public CreateCar(Repository repository){
        mRepository = repository;
        MOCK_CAR = new Car();
        MOCK_CAR.setTitle("");
    }

    @Override
    public Observable<UpsertCarResult> execute(Car data) {
        return mRepository.getCarsNetwork()
                .flatMap(cars -> {
                    Log.d("++++", "execute: "+cars.size());
                    if (cars.size()>0)
                        return Observable.from(cars);
                    return Observable.just(MOCK_CAR);})
                .exists(car -> car.getTitle().equals(data.getTitle()))
                .flatMap(existsCar -> {
                    Log.d("++++", "execute: "+existsCar+" "+data.getTitle());
                    if (existsCar)
                        return Observable.just(new UpsertCarResult());
                    return mRepository.upsertCarNetwork(data);})
                .map(upsertCarResult -> {
                    if (upsertCarResult.isSuccess())
                        mRepository.getCarsNetwork()
                                .flatMap(Observable::from)
                                .filter(car -> car.getTitle().equals(data.getTitle()))
                                .flatMap(mRepository::createCarDB)
                                .subscribe();
                    return upsertCarResult;});
    }
}
