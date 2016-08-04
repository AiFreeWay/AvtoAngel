package upplic.com.angelavto.domain.executors;


import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Login;
import upplic.com.angelavto.domain.models.SendCodeRequestResult;
import upplic.com.angelavto.domain.repositories.Repository;

public class Registration implements Interactor1<SendCodeRequestResult, Login> {

    private Repository mRepository;

    @Inject
    public Registration(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable execute(Login data) {
        return mRepository.registration(data);
    }
}
