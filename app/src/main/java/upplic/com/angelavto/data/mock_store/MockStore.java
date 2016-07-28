package upplic.com.angelavto.data.mock_store;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Beacon;

public class MockStore {

    private List<Beacon> mBeacons;
    private List<Car> mCars;

    public MockStore() {
        mBeacons = new LinkedList<Beacon>();
        mBeacons.add(new Beacon("Тип №1", "Харакстеристики", "1700 руб.", ""));
        mBeacons.add(new Beacon("Тип №1", "Харакстеристики", "1700 руб.", ""));
        mBeacons.add(new Beacon("Тип №1", "Харакстеристики", "1700 руб.", ""));

        mCars = new ArrayList<Car>();
        mCars.add(new Car(0, "Тесла", Car.STATE_LOCK));
        mCars.add(new Car(1, "Москвич", Car.STATE_LOCK));
        mCars.add(new Car(2, "Мазда", Car.STATE_UNLOCK));
    }

    public List<Beacon> getGetBeacons() {
        return mBeacons;
    }

    public List<Car> getCars() {
        return mCars;
    }

    public List<Car> updateCars(Car car) {
        int findedCarId = -1;
        for (int i=0; i<mCars.size(); i++) {
            Car carItem = mCars.get(i);
            if (carItem.getId() == car.getId()) {
                findedCarId = i;
                break;
            }
        }
        if (findedCarId != -1)
            mCars.add(findedCarId, car);
        else
            mCars.add(car);
        return mCars;
    }

    public Car getCarById(int id) throws Exception {
        return mCars.get(id);
    }
}
