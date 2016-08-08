package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.repositories.Repository;

public class Login implements Interactor1<LoginResult, LoginDomain> {

    private Repository mRepository;

    @Inject
    public Login(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<LoginResult> execute(LoginDomain data) {
        return mRepository.login(data);
    }
}
