package upplic.com.angelavto.domain.executors;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;
import upplic.com.angelavto.domain.repositories.Repository;


public class Registration implements Interactor1<RegistrationResult, RegistrationDomain> {

    private Repository mRepository;

    @Inject
    public Registration(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<RegistrationResult> execute(RegistrationDomain data) {
        return mRepository.registration(data);
    }
}
