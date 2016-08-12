package upplic.com.angelavto.presentation.mappers;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;
import upplic.com.angelavto.presentation.wrappers.AbstractHundleMemento;
import upplic.com.angelavto.presentation.wrappers.ActivityHandleMemento;
import upplic.com.angelavto.presentation.wrappers.FragmentHandleMemento;

public class CarMapper {

    public static List<AppMenuItem> mapCars(Context context, List<Car> cars) {
        ArrayList<AppMenuItem> joinMenu = new ArrayList<AppMenuItem>();
        for (Car car : cars)
            joinMenu.add(mapCars(context, car));
        return joinMenu;
    }

    public static AppMenuItem mapCars(Context context, Car car) {
        int drawable;
        if (car.isStatus())
            drawable = R.drawable.ic_lock_green;
        else
            drawable = R.drawable.ic_lock_red;

        Bundle bundle = new Bundle();
        bundle.putSerializable(AvtoFragment.CAR_OPTIONS_TAG, mapCar(car));
        return new AppMenuItem(car.getTitle(), drawable, new FragmentHandleMemento(FragmentsFactory.Fragments.AVTO, bundle, AbstractHundleMemento.MenuHandlers.FRAGMENT));
    }

    private static CarOptions mapCar(Car car) {
        CarOptions carOptions = new CarOptions();
        carOptions.setId(car.getId());
        carOptions.setTitle(car.getTitle());
        return carOptions;
    }
}
