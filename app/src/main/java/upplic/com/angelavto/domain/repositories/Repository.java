package upplic.com.angelavto.domain.repositories;


import java.util.List;

import rx.Observable;
import rx.Single;
import rx.subjects.ReplaySubject;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Login;
import upplic.com.angelavto.domain.models.SendCodeRequestResult;

public interface Repository {

    List<Beacon> getBeacons() throws Exception;
    Car getCarById(int id) throws Exception;
    ReplaySubject<List<Car>> getCars();
    Observable<CarTableEntity> createCar(Car car);
    boolean canCreateCar(Car car);
    Observable<CarTableEntity> updateCar(Car car);
    Observable<Integer> deleteCar(Car car);
    Observable registration(Login login);
}
