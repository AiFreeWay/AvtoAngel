package upplic.com.angelavto.data.repositories;


import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;
import rx.Single;
import rx.subjects.ReplaySubject;
import upplic.com.angelavto.data.db_store.SqliteController;
import upplic.com.angelavto.data.db_store.table_controllers.CarDBController;
import upplic.com.angelavto.data.db_store.tables.CarTable;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.data.mock_store.MockStore;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.repositories.Repository;

@Singleton
public class RepositoryImpl implements Repository {

    private MockStore mMockStore;
    private ReplaySubject<List<Car>> mCarSubject;
    private CarDBController mCarDBController;

    @Inject
    public RepositoryImpl(Context context) {
        SqliteController dBStore = new SqliteController(context);
        mCarDBController = dBStore.getCarDBController();
        mMockStore = new MockStore();
        mCarSubject = ReplaySubject.create();
        mCarSubject.onNext(CarMapper.mapCarsDB(mCarDBController.getCars()));
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
    public Observable<CarTableEntity> createCar(Car car) {
        return mCarDBController.createCar(car)
                .doOnCompleted(() -> mCarSubject.onNext(CarMapper.mapCarsDB(mCarDBController.getCars())));
    }

    @Override
    public Car getCarById(int id) throws Exception {
        return null;
    }

    @Override
    public boolean canCreateCar(Car car) {
        return mCarDBController.canCreateCar(car);
    }

    @Override
    public Observable<CarTableEntity> updateCar(Car car) {
        return mCarDBController.updateCar(car)
                .doOnCompleted(() -> mCarSubject.onNext(CarMapper.mapCarsDB(mCarDBController.getCars())));
    }

    @Override
    public Observable<Integer> deleteCar(Car car) {
        return mCarDBController.deleteCar(car)
                .doOnCompleted(() -> mCarSubject.onNext(CarMapper.mapCarsDB(mCarDBController.getCars())));
    }
}
