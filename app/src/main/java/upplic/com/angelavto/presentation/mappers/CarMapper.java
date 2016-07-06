package upplic.com.angelavto.presentation.mappers;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.utils.FragmentsFactory;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;
import upplic.com.angelavto.presentation.wrappers.FragmentHandleMemento;

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

        Bundle args = new Bundle();
        args.putInt(AvtoFragment.CAR_ID, car.getId());
        return new AppMenuItem(car.getTitle(), drawable, new FragmentHandleMemento(FragmentsFactory.Fragments.AVTO, args));
    }
}
