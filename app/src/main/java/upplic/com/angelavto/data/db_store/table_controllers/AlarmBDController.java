package upplic.com.angelavto.data.db_store.table_controllers;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;
import upplic.com.angelavto.data.db_store.tables.AlarmTableEntity;
import upplic.com.angelavto.data.mappers.AlarmMapper;
import upplic.com.angelavto.domain.models.Alarm;


public class AlarmBDController {

    private SingleEntityStore<Persistable> mDataStore;

    public AlarmBDController(SingleEntityStore<Persistable> dataStore) {
        mDataStore = dataStore;
    }

    public Observable<Integer> deleteAllAlarms() {
        return mDataStore.delete(AlarmTableEntity.class)
                .get()
                .toSingle()
                .toObservable();
    }

    public Observable<AlarmTableEntity> getAlarmByCarId(int id) {
        return mDataStore.select(AlarmTableEntity.class)
                .where(AlarmTableEntity.ID.eq(id))
                .get()
                .toObservable();
    }

    public Observable<Integer> deleteAlarmByCarId(int id) {
        return mDataStore.delete(AlarmTableEntity.class)
                .where(AlarmTableEntity.ID.eq(id))
                .get()
                .toSingle()
                .toObservable();
    }

    public Observable<AlarmTableEntity> insertAlarm(Alarm alarm) {
        return mDataStore.insert(AlarmMapper.mapAlarm(alarm))
                .toObservable();
    }
}
