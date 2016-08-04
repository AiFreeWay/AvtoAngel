package upplic.com.angelavto.data.db_store.table_controllers;


import android.util.Log;

import java.util.List;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;

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

    public Observable<CarTableEntity> createCar(Car car) {
        return mDataStore.insert(CarMapper.mapCar(car))
                .toObservable()
                .subscribeOn(Schedulers.newThread())
                .doOnError(e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "SqliteController: createCar error "+e.toString()));
    }

    public boolean canCreateCar(Car car) {
        List carsList = mDataStore.select(CarTableEntity.class)
                .where(CarTableEntity.TITLE.like(car.getTitle()))
                .get()
                .toList();
        return carsList.size() == 0;
    }

    public Observable<CarTableEntity> getCarById(int id) {
        return mDataStore.select(CarTableEntity.class)
                .where(CarTableEntity.ID.eq(id))
                .get()
                .toObservable();
    }

    public Observable<CarTableEntity> updateCar(Car car) {
        return getCarById(car.getId())
                .flatMap(entity -> mDataStore.update(CarMapper.fillEntityModelData(entity, car))
                        .toObservable());
    }

    public Observable<Integer> deleteCar(Car car) {
        return mDataStore.delete(CarTableEntity.class)
                .where(CarTableEntity.ID.eq(car.getId()))
                .get()
                .toSingle()
                .toObservable();
    }
}
