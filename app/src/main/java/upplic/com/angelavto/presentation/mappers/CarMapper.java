package upplic.com.angelavto.presentation.mappers;


import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.views.activities.AvtoActivity;
import upplic.com.angelavto.presentation.wrappers.AbstractHundleMemento;
import upplic.com.angelavto.presentation.wrappers.ActivityHandleMemento;

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

        Intent intent = new Intent(context, AvtoActivity.class);
        intent.putExtra(AvtoActivity.CAR_TAG, car);
        return new AppMenuItem(car.getTitle(), drawable, new ActivityHandleMemento(intent, AbstractHundleMemento.MenuHandlers.ACTIVITY));
    }
}
