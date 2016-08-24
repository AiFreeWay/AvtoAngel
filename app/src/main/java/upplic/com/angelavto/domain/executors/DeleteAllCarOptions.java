package upplic.com.angelavto.domain.executors;


import android.util.Log;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.repositories.Repository;

public class DeleteAllCarOptions implements Interactor0 {

    private Repository mRepository;

    @Inject
    public DeleteAllCarOptions(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable execute() {
        return mRepository.deleteAllCarOptions();
    }
}
