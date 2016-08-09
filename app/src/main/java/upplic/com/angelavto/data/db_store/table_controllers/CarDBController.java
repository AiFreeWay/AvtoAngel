package upplic.com.angelavto.data.db_store.table_controllers;

import java.util.List;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.domain.models.Car;


public class CarDBController {

    private SingleEntityStore<Persistable> mDataStore;

    public CarDBController(SingleEntityStore<Persistable> dataStore) {
        mDataStore = dataStore;
    }

    public List<CarTableEntity> getCars() {
        return mDataStore.select(CarTableEntity.class)
                .get()
                .toList();
    }

    public Observable<CarTableEntity> updateCar(Car car) {
        return mDataStore.update(CarMapper.mapCarToDB(car))
                .toObservable();
    }

    public Observable<Integer> deleteCar(Car car) {
        return mDataStore.delete(CarTableEntity.class)
                .where(CarTableEntity.ID.eq(car.getId()))
                .get()
                .toSingle()
                .toObservable();
    }
}
