package upplic.com.angelavto.presentation.mappers;


import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.models.AppMenuItem;

public class CarMapper {

    public static List<AppMenuItem> mapCars(List<Car> cars) {
        ArrayList<AppMenuItem> joinMenu = new ArrayList<AppMenuItem>();
        for (Car car : cars)
            joinMenu.add(mapCars(car));
        return joinMenu;
    }

    public static AppMenuItem mapCars(Car car) {
        int drawable;
        if (car.getState() == Car.STATE_LOCK)
            drawable = R.drawable.ic_lock_green;
        else
            drawable = R.drawable.ic_lock_red;
        return new AppMenuItem(car.getTitle(), drawable);
    }
}
