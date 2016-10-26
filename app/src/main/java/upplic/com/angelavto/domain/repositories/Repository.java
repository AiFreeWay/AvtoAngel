package upplic.com.angelavto.domain.repositories;

import java.util.List;

import rx.Observable;
import upplic.com.angelavto.domain.models.Alarm;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.domain.models.UpsertCarResult;


public interface Repository {

    Observable<List<Beacon>> getBeacons();
    Observable<RegistrationResult> registration(RegistrationDomain registrationDomain);
    Observable<LoginResult> login(LoginDomain loginDomain);

    Observable<CarOptions> updateCarOptions(CarOptions carOptions);
    Observable<List<CarOptions>> getCarsOptions();
    Observable<Integer>  deleteAllCarOptions();
    Observable<Integer>  deleteAllAlarms();
    void upsertCarOptions(Car car);
    Observable<Integer> insertAlarm(Alarm alarm);
    void updateCarOptionsEditTime(int id);
    void deleteCarOptions(int id);
    Observable<Integer> deleteAlarmByCarId(int id);
    Observable<Alarm> getAlarmByCarId(int id);
    Observable<List<Alarm>> getAlarms();
    Observable<Integer> putAlarms(List<Alarm> alarms);
    void updateCarOptionsFromNetwork(List<Car> cars);

    Observable<List<Car>> getCarsNetworkEmit();
    Observable<List<Car>> getCarsNetwork();
    Observable<UpsertCarResult> upsertCarNetwork(Car car);
    Observable<DeleteCarResult> deleteCarNetwork(Car car);
    Observable<Car> getCarDetailNetwork(int id);
    Observable<Boolean> checkKey();
    Observable<Status> setStatus(Status status);
    Observable<List<Record>> getRecords(int carId);
    Observable<Record> getRecordDetail(int id);
    Observable<List<Alarm>> checkAlarm();
    Observable<String> sendGcmToken(String token);
    Observable<String> offAlarm();
}
