package upplic.com.angelavto.domain.repositories;


import java.util.List;

import rx.subjects.ReplaySubject;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;

public interface Repository {

    List<Beacon> getBeacons() throws Exception;
    ReplaySubject<List<Car>> getCars();
    void createOrUdateCar(Car car) throws Exception;
    Car getCarById(int id) throws Exception;
}
