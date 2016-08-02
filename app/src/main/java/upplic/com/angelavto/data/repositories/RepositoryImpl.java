package upplic.com.angelavto.data.repositories;


import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Single;
import rx.subjects.ReplaySubject;
import upplic.com.angelavto.data.db_store.SqliteController;
import upplic.com.angelavto.data.db_store.tables.CarTable;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.data.mock_store.MockStore;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;

@Singleton
public class RepositoryImpl implements Repository {

    private MockStore mMockStore;
    private ReplaySubject<List<Car>> mCarSubject;
    private SqliteController  mDBStore;

    @Inject
    public RepositoryImpl(Context context) {
        mDBStore = new SqliteController(context);
        mMockStore = new MockStore();
        mCarSubject = ReplaySubject.create();
        mCarSubject.onNext(CarMapper.mapCarsDB(mDBStore.getCars()));
    }

    @Override
    public List<Beacon> getBeacons() throws Exception {
         return mMockStore.getGetBeacons();
    }

    @Override
    public ReplaySubject<List<Car>> getCars() {
        return mCarSubject;
    }

    @Override
    public void createCar(Car car) throws Exception {
        mDBStore.createCar(car);
        mCarSubject.onNext(CarMapper.mapCarsDB(mDBStore.getCars()));
    }

    @Override
    public Car getCarById(int id) throws Exception {
        return mMockStore.getCarById(id);
    }
}
