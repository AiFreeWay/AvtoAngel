package upplic.com.angelavto.data.db_store;


import android.content.Context;
import android.os.StrictMode;

import io.requery.Persistable;
import io.requery.android.BuildConfig;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.meta.EntityModel;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;
import upplic.com.angelavto.data.db_store.table_controllers.CarDBController;
import upplic.com.angelavto.data.db_store.tables.Schema;

public class SqliteController {

    private final EntityModel TABLE_SCHEMA = new Schema();

    private SingleEntityStore<Persistable> mDataStore;
    private Context mContext;
    private CarDBController mCarDBController;

    public SqliteController(Context context) {
        mContext = context;
        StrictMode.enableDefaults();
        initStore();
        mCarDBController = new CarDBController(mDataStore);
    }

    private void initStore() {
        DatabaseSource source = new DatabaseSource(mContext, TABLE_SCHEMA, 1);
        if (BuildConfig.DEBUG)
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        Configuration configuration = source.getConfiguration();
        mDataStore = RxSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }

    public CarDBController getCarDBController() {
        return mCarDBController;
    }
}
