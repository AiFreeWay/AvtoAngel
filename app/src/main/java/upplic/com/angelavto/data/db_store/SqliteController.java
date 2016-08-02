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
import upplic.com.angelavto.data.db_store.tables.CarTable;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.data.db_store.tables.Tables;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;

public class SqliteController {

    private final EntityModel TABLE_SCHEMA = new Tables();

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

    public void createCar(Car car) throws Exception {
        CarTableEntity carDB = CarMapper.mapCar(car);
        mDataStore.insert(carDB)
                .doOnError(e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "SqliteController: createCar error "+e.toString()))
                .subscribe();
    }
}
