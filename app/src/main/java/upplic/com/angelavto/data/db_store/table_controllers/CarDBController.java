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

    public Observable<CarTableEntity> upsertCar(Car car) {
        return mDataStore.upsert(CarMapper.mapCarToDB(car))
                .toObservable();
    }

    public Observable<Integer> deleteCar(Car car) {
        return null;
    }

    public void updateCarDBFromNetwork(List<Car> cars) {
        for (Car car : cars)
            mDataStore.update(CarTableEntity.class)
                    .set(CarTableEntity.TITLE, car.getTitle())
                    .set(CarTableEntity.TRACKER_NUMBER, car.getTrackerNumber())
                    .set(CarTableEntity.TRACKER_TYPE, car.getTrackerType())
                    .set(CarTableEntity.STATUS, car.isStatus())
                    .get()
                    .toSingle()
                    .subscribe();
    }
}
