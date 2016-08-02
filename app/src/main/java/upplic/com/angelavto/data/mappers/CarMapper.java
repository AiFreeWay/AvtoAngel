package upplic.com.angelavto.data.mappers;


import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.data.db_store.tables.CarTable;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.domain.models.Car;

public class CarMapper {

    public static List<Car> mapCarsDB(List<CarTableEntity> carsDB) {
        List<Car> cars = new ArrayList<>();
        for (CarTable carDB : carsDB)
            cars.add(mapCarDB(carDB));
        return cars;
    }

    public static Car mapCarDB(CarTable carDB) {
        return new Car(carDB.getId(), carDB.getTitle(), carDB.getSecuritynState(), carDB.getNotificationState(), carDB.getTrackerNumber());
    }

    public static List<CarTableEntity> mapCars(List<Car> cars) {
        List<CarTableEntity> carsDB = new ArrayList<>();
        for (Car car : cars)
            carsDB.add(mapCar(car));
        return carsDB;
    }

    public static CarTableEntity mapCar(Car car) {
        CarTableEntity carDB = new CarTableEntity();
        carDB.setTitle(car.getTitle());
        carDB.setSecuritynState(car.getSequrityState());
        carDB.setNotificationState(car.getNotificationState());
        carDB.setTrackerNumber(car.getPhone());
        return carDB;
    }
}
