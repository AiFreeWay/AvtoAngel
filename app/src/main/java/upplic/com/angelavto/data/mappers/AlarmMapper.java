package upplic.com.angelavto.data.mappers;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.data.db_store.tables.AlarmTableEntity;
import upplic.com.angelavto.data.models.AlarmData;
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

    public static Alarm mapAlarm(AlarmData alarmNetwork) {
        Alarm alarm = new Alarm();
        alarm.setCarId(Integer.parseInt(alarmNetwork.getCarId()));
        alarm.setTitle(alarmNetwork.getTitle());
        alarm.setStatus(Alarm.AlarmTypes.values()[Integer.parseInt(alarmNetwork.getStatus())]);
        return alarm;
    }

    public static List<Alarm> mapAlarmsFromDB(List<AlarmTableEntity> alarmsFromDB) {
        List<Alarm> alarms = new ArrayList<>();
        for (AlarmTableEntity alarm : alarmsFromDB)
            alarms.add(mapAlarm(alarm));
        return alarms;
    }

    public static List<Alarm> mapAlarmsFromNetwork(List<AlarmData> alarmsFromNet) {
        List<Alarm> alarms = new ArrayList<>();
        for (AlarmData alarm : alarmsFromNet)
            alarms.add(mapAlarm(alarm));
        return alarms;
    }

    public static List<AlarmTableEntity> mapAlarmsToDB(List<Alarm> alarms) {
        List<AlarmTableEntity> alarmsDB = new ArrayList<>();
        for (Alarm alarm : alarms)
            alarmsDB.add(mapAlarm(alarm));
        return alarmsDB;
    }
}
