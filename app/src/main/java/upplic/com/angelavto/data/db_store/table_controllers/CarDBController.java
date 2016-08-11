package upplic.com.angelavto.data.db_store.table_controllers;


import java.util.List;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;
import upplic.com.angelavto.data.db_store.tables.CarOptionsTableEntity;
import upplic.com.angelavto.data.mappers.CarOptionsMapper;
import upplic.com.angelavto.domain.models.Car;


public class CarDBController {

    private SingleEntityStore<Persistable> mDataStore;

    public CarDBController(SingleEntityStore<Persistable> dataStore) {
        mDataStore = dataStore;
    }

    public Observable<CarOptionsTableEntity> upsertCar(Car car) {
        return mDataStore.upsert(CarOptionsMapper.mapCarOptions(car))
                .toObservable();
    }

    public void deleteCar(Car car) {
        mDataStore.delete(CarOptionsTableEntity.class)
                .where(CarOptionsTableEntity.ID.eq(car.getId()))
                .get()
                .toSingle()
                .subscribe();
    }

    public void updateCarDBFromNetwork(List<Car> cars) {
        for (Car car : cars)
            mDataStore.update(CarOptionsTableEntity.class)
                    .set(CarOptionsTableEntity.TITLE, car.getTitle())
                    .get()
                    .toSingle()
                    .subscribe();
    }

    public List<CarOptionsTableEntity> getCars() {
        return mDataStore.select(CarOptionsTableEntity.class)
                .get()
                .toList();
    }
}
