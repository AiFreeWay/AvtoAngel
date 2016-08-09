package upplic.com.angelavto.data.mappers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import upplic.com.angelavto.data.db_store.tables.CarTable;
import upplic.com.angelavto.data.db_store.tables.CarTableEntity;
import upplic.com.angelavto.data.net_store.requests_entityes.UpsertCarRequest;
import upplic.com.angelavto.data.net_store.response_entityes.GetCarsResponse;
import upplic.com.angelavto.data.net_store.response_entityes.UpsertCarResponse;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.UpsertCarResult;

public class CarMapper {

    public static List<Car> mapCarsFromDB(List<CarTableEntity> carsDB) {
        List<Car> cars = new ArrayList<>();
        for (CarTable carDB : carsDB)
            cars.add(mapCarFromDB(carDB));
        return cars;
    }

    public static Car mapCarFromDB(CarTable carDB) {
        return new Car(carDB.getId(), carDB.getTitle(), carDB.getStatus(), carDB.getNotification(), carDB.getTrackerNumber(), carDB.getTrackerType());
    }

    public static List<CarTableEntity> mapCarsToDB(List<Car> cars) {
        List<CarTableEntity> carsDB = new ArrayList<>();
        for (Car car : cars)
            carsDB.add(mapCarToDB(car));
        return carsDB;
    }

    public static CarTableEntity mapCarToDB(Car car) {
        CarTableEntity carDB = new CarTableEntity();
        carDB.setId(car.getId());
        carDB.setTitle(car.getTitle());
        carDB.setStatus(car.isStatus());
        carDB.setNotification(car.isNotification());
        carDB.setTrackerNumber(car.getTrackerNumber());
        return carDB;
    }

    public static List<Car> mapCarsFromNetwork(GetCarsResponse getCarsResponse) {
        return Arrays.asList(getCarsResponse.getResult());
    }

    public static UpsertCarRequest mapCarsToNetwork(String key, Car car) {
        return new UpsertCarRequest(key, car);
    }

    public static UpsertCarResult mapUpsertCarNetwork(UpsertCarResponse upsertCarResponse) {
        return new UpsertCarResult(upsertCarResponse.getResult());
    }
}
