package upplic.com.angelavto.domain.executors;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.domain.repositories.Repository;


public class UpdateCar implements Interactor1<UpsertCarResult, Car> {

    private Repository mRepository;

    @Inject
    public UpdateCar(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<UpsertCarResult> execute(Car data) {
        return mRepository.upsertCarNetwork(data)
                .map(upsertCarResult -> {
                    mRepository.upsertCarDB(data);
                    return upsertCarResult;});
    }
}