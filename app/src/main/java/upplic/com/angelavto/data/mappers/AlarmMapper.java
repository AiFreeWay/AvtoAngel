package upplic.com.angelavto.data.mappers;

import upplic.com.angelavto.data.db_store.tables.AlarmTableEntity;
import upplic.com.angelavto.domain.models.Alarm;

public class AlarmMapper {


    public static AlarmTableEntity mapAlarm(Alarm alarm) {
        AlarmTableEntity alarmDB = new AlarmTableEntity();
        alarmDB.setId(alarm.getCarId());
        alarmDB.setTitle(alarm.getTitle());
        alarmDB.setStatus(alarm.getStatus().id);
        return alarmDB;
    }

    public static Alarm mapAlarm(AlarmTableEntity alarmDB) {
        Alarm alarm = new Alarm();
        alarm.setCarId(alarmDB.getId());
        alarm.setTitle(alarmDB.getTitle());
        alarm.setStatus(Alarm.AlarmTypes.values()[alarmDB.getStatus()]);
        return alarm;
    }
}
