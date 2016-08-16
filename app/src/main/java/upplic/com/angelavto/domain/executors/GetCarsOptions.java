package upplic.com.angelavto.domain.executors;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.repositories.Repository;

public class GetCarsOptions implements Interactor0<List<CarOptions>> {

    private Repository mRepository;

    @Inject
    public GetCarsOptions(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<List<CarOptions>> execute() {
        return mRepository.getCarsOptions();
    }
}