package upplic.com.angelavto.data.db_store.table_controllers;

import java.util.List;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;
import upplic.com.angelavto.data.db_store.tables.CarOptionsTableEntity;
import upplic.com.angelavto.data.mappers.CarOptionsMapper;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;

public class CarOptionsDBController {

    private SingleEntityStore<Persistable> mDataStore;

    public CarOptionsDBController(SingleEntityStore<Persistable> dataStore) {
        mDataStore = dataStore;
    }

    public void deleteCarOptions(int id) {
        mDataStore.delete(CarOptionsTableEntity.class)
                .where(CarOptionsTableEntity.ID.eq(id))
                .get()
                .toSingle()
                .subscribe();
    }

    public void upsertCarOptionsFromNetwork(List<Car> cars) {
        for (Car car : cars)
            upsertCarOptionsFromNetwork(car);

    }

    public void upsertCarOptionsFromNetwork(Car car) {
        List<CarOptionsTableEntity> findedCarOptions = mDataStore.select(CarOptionsTableEntity.class)
                .where(CarOptionsTableEntity.ID.eq(car.getId()))
                .get().toList();
        if (findedCarOptions.size() == 0)
            insertCarOptions(car);
        else
            updateCarOptionsFromNetwork(car);
    }

    public List<CarOptionsTableEntity> getCarOptions() {
        return mDataStore.select(CarOptionsTableEntity.class)
                .get()
                .toList();
    }

    public Observable<CarOptionsTableEntity> updateCarOptions(CarOptions carOptions) {
        return mDataStore.update(CarOptionsMapper.mapCarOptions(carOptions))
                .toObservable();

    }

    public Observable<Integer> deleteAllCarOptions() {
        return mDataStore.delete(CarOptionsTableEntity.class)
                .get()
                .toSingle()
                .toObservable();
    }

    public void updateCarOptionsEditTime(int id) {
        mDataStore.update(CarOptionsTableEntity.class)
                .set(CarOptionsTableEntity.EDIT_DATE, System.nanoTime())
                .where(CarOptionsTableEntity.ID.eq(id))
                .get()
                .toSingle()
                .subscribe();
    }

    private void insertCarOptions(Car car) {
        mDataStore.insert(CarOptionsMapper.mapCarOptions(car))
                .subscribe();
    }

    private void updateCarOptionsFromNetwork(Car car) {
        mDataStore.update(CarOptionsTableEntity.class)
                .set(CarOptionsTableEntity.TITLE, car.getTitle())
                .where(CarOptionsTableEntity.ID.eq(car.getId()))
                .get()
                .toSingle()
                .subscribe();
    }
}
