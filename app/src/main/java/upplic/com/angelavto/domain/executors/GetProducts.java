package upplic.com.angelavto.domain.executors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.domain.repositories.Repository;


public class GetProducts implements Interactor0<List<Product>> {

    private Repository mRepository;

    @Inject
    public GetProducts(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<List<Product>> execute() {
        Observable.OnSubscribe<List<Product>> subscriber = observer -> {
            try {
                observer.onNext(mRepository.getProducts());
            } catch (Exception e) {
                observer.onError(e);
            }
            observer.onCompleted();
        };
        return Observable.create(subscriber);
    }
}
