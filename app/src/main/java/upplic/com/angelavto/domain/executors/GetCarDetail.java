package upplic.com.angelavto.domain.executors;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;


public class GetCarDetail implements Interactor1<Car, Integer> {

    private Repository mRepository;

    @Inject
    public GetCarDetail(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Car> execute(Integer data) {
        return mRepository.getCarDetailNetwork(data)
                .map(car -> { mRepository.upsertCarDB(car).subscribe();
                        return car;});
    }
}
