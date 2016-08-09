package upplic.com.angelavto.domain.executors;

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
        return mRepository.upsertCarNetwork(data);
    }
}
