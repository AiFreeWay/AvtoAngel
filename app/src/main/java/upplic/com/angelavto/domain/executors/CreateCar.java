package upplic.com.angelavto.domain.executors;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.domain.repositories.Repository;


public class CreateCar implements Interactor1<UpsertCarResult, Car> {

    private Repository mRepository;

    @Inject
    public CreateCar(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<UpsertCarResult> execute(Car data) {
        return checkExistsCar(data)
                .flatMap(isExists -> {
                    if (isExists)
                        return Observable.just(new UpsertCarResult());
                    return mRepository.upsertCarNetwork(data)
                            .map(upsertCarResult -> {
                                upsertCarToDB(data);
                                return upsertCarResult;});});
    }

    private Observable<Boolean> checkExistsCar(Car car) {
        return mRepository.getCarsNetwork()
                .flatMap(cars -> {
                    for (Car carNetwork : cars)
                        if ((car.getTitle().equals(carNetwork.getTitle())))
                            return Observable.just(true);
                    return Observable.just(false);});
    }

    private void upsertCarToDB(Car car) {
        mRepository.getCarsNetwork()
                .subscribe(cars -> {
                    for (Car carNetwork : cars)
                        if ((car.getTitle().equals(carNetwork.getTitle()))) {
                            mRepository.upsertCarOptions(carNetwork);
                            break;}})
                .unsubscribe();
    }
}
