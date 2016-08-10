package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.repositories.Repository;

public class DeleteCar implements Interactor1<DeleteCarResult, Car> {

    private Repository mRepository;

    @Inject
    public DeleteCar(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<DeleteCarResult> execute(Car data) {
        return mRepository.deleteCarNetwork(data)
                .map(deleteCarResult -> {
                    mRepository.deleteCarDB(data);
                    mRepository.getCarsNetworkEmit().subscribe();
                    return deleteCarResult;});
    }
}
