package upplic.com.angelavto.presentation.utils;


import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.models.AppMenuItem;

public class AppMenuFactory {

    private ArrayList<AppMenuItem> mMenu;

    public AppMenuFactory() {
        mMenu = new ArrayList<AppMenuItem>();
        generateItems();
    }

    private void generateItems() {
        ArrayList<AppMenuItem> autos = new ArrayList<AppMenuItem>();
        autos.add(new AppMenuItem("Тесла", R.drawable.ic_lock_green));
        autos.add(new AppMenuItem("Москвич", R.drawable.ic_lock_green));
        autos.add(new AppMenuItem("Мазда", R.drawable.ic_lock_red));
        mMenu.add(new AppMenuItem("Автомобили", R.drawable.ic_auto, autos));
        mMenu.add(new AppMenuItem("Купить трекер", R.drawable.ic_shop));
        mMenu.add(new AppMenuItem("О программе", R.drawable.ic_about));
        mMenu.add(new AppMenuItem("Выйти", R.drawable.ic_exit));
    }

    public List<AppMenuItem> getMenu() {
        return mMenu;
    }
}
