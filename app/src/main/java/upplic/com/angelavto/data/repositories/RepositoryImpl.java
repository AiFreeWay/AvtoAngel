package upplic.com.angelavto.data.repositories;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import upplic.com.angelavto.data.mock_store.MockStore;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.domain.repositories.Repository;

@Singleton
public class RepositoryImpl implements Repository {

    private MockStore mMockStore;
    private ReplaySubject<List<Car>> mCarSubject;

    @Inject
    public RepositoryImpl() {
        mMockStore = new MockStore();
        mCarSubject = ReplaySubject.create();
        mCarSubject.onNext(mMockStore.getCars());
    }

    @Override
    public List<Product> getProducts() throws Exception {
        throw new Exception("wad");
        //return mMockStore.getProducts();
    }

    @Override
    public ReplaySubject<List<Car>> getCars() {
        return mCarSubject;
    }

    @Override
    public void createOrUdateCar(Car car) throws Exception {
        mCarSubject.onNext(mMockStore.updateCars(car));
    }
}
