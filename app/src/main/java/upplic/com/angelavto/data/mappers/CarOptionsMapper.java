package upplic.com.angelavto.data.mappers;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.data.db_store.tables.CarOptionsTableEntity;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;

public class CarOptionsMapper {

    public static CarOptionsTableEntity mapCarOptions(Car car) {
        CarOptionsTableEntity carDB = new CarOptionsTableEntity();
        carDB.setId(car.getId());
        carDB.setTitle(car.getTitle());
        carDB.setEditDate(System.nanoTime());
        return carDB;
    }

    public static CarOptionsTableEntity mapCarOptions(CarOptions carOptions) {
        CarOptionsTableEntity carDB = new CarOptionsTableEntity();
        carDB.setId(carOptions.getId());
        carDB.setTitle(carOptions.getTitle());
        carDB.setNotification(carOptions.isNotification());
        carDB.setEditDate(System.nanoTime());
        return carDB;
    }

    public static List<CarOptions> mapCarOptions(List<CarOptionsTableEntity> carsFromDB) {
        List<CarOptions> cars = new ArrayList<>();
        for (CarOptionsTableEntity car : carsFromDB)
            cars.add(mapCarOptions(car));
        return cars;
    }

    public static CarOptions mapCarOptions(CarOptionsTableEntity carDB) {
        CarOptions carOptions = new CarOptions();
        carOptions.setId(carDB.getId());
        carOptions.setTitle(carDB.getTitle());
        carOptions.setEditDate(carDB.getEditDate());
        carOptions.setNotification(carDB.getNotification());
        return carOptions;
    }
}
