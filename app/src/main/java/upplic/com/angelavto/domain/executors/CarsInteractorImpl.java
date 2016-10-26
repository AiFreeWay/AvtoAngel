package upplic.com.angelavto.domain.executors;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.domain.repositories.Repository;

public class CarsInteractorImpl implements CarsInteractor {

    private Repository mRepository;

    @Inject
    public CarsInteractorImpl(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<UpsertCarResult> createCar(Car data) {
        return checkExistsCarTitle(data)
                .flatMap(isExists -> {
                    if (isExists)
                        return Observable.just(new UpsertCarResult());
                    return mRepository.upsertCarNetwork(data);});
    }

    @Override
    public Observable<DeleteCarResult> deleteCar(Car data) {
        return mRepository.deleteCarNetwork(data)
                .map(deleteCarResult -> {
                    mRepository.deleteCarOptions(data.getId());
                    mRepository.getCarsNetworkEmit().subscribe();
                    return deleteCarResult;});
    }

    @Override
    public Observable<List<Car>> getCarsSubject() {
        return mRepository.getCarsNetworkEmit()
                .cache();
    }

    @Override
    public Observable<List<Car>> getCars() {
        return mRepository.getCarsNetwork()
                .cache();
    }

    @Override
    public Observable<UpsertCarResult> updateCar(Car data) {
        return mRepository.upsertCarNetwork(data);
    }

    @Override
    public Observable<Car> getCarDetail(Integer data) {
        return mRepository.getCarDetailNetwork(data)
                .map(car -> { mRepository.updateCarOptionsEditTime(data);
                    return car;});
    }

    private Observable<Boolean> checkExistsCarTitle(Car car) {
        return mRepository.getCarsNetwork()
                .flatMap(cars -> {
                    for (Car carNetwork : cars)
                        if (car.getTitle().equals(carNetwork.getTitle()))
                            return Observable.just(true);
                    return Observable.just(false);});
    }
}
