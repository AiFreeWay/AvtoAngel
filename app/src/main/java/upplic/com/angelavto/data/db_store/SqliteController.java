package upplic.com.angelavto.data.db_store;


import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import java.util.List;

import io.requery.Persistable;
import io.requery.android.BuildConfig;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.meta.EntityModel;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;
import rx.Observable;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.data.db_store.tables.CarTable;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.data.db_store.tables.Schema;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;

public class SqliteController {

    private final EntityModel TABLE_SCHEMA = new Schema();

    private SingleEntityStore<Persistable> mDataStore;
    private Context mContext;

    public SqliteController(Context context) {
        mContext = context;
        StrictMode.enableDefaults();
        initStore();
    }

    private void initStore() {
        DatabaseSource source = new DatabaseSource(mContext, TABLE_SCHEMA, 1);
        if (BuildConfig.DEBUG)
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        Configuration configuration = source.getConfiguration();
        mDataStore = RxSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
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

    public Observable updateCar(Car car) {
        return getCarById(car.getId())
                .flatMap(entity -> mDataStore.update(CarMapper.fillEntityModelData(entity, car))
                        .toObservable());
    }
}
