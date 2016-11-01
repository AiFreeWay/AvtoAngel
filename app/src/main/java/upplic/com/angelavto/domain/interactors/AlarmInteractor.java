package upplic.com.angelavto.domain.interactors;

import java.util.List;

import rx.Observable;
import upplic.com.angelavto.domain.models.Alarm;


public interface AlarmInteractor {

    Observable<Integer> deleteAllAlarms();
    Observable<Integer> deleteAlarmById(int id);
    Observable<Integer> insertAlarm(Alarm alarm);
    Observable<Alarm> getAlarmByCarId(int id);
    Observable<List<Alarm>> getAlarmsFromDB();
    Observable<List<Alarm>> getAlarmsFromNetwork();
    Observable<Integer> putAlarms(List<Alarm> alarms);
    Observable<String> offAlarm();
}
