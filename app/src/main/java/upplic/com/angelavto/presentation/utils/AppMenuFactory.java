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
        mMenu.add(MenuItems.AVTO.id, new AppMenuItem("Автомобили", R.drawable.ic_auto));
        mMenu.add(MenuItems.SHOP.id, new AppMenuItem("Купить трекер", R.drawable.ic_shop));
        mMenu.add(MenuItems.ABOUT.id, new AppMenuItem("О программе", R.drawable.ic_about));
        mMenu.add(MenuItems.EXIT.id, new AppMenuItem("Выйти", R.drawable.ic_exit));
    }

    public List<AppMenuItem> getMenu() {
        return mMenu;
    }

    public AppMenuItem getFragment(MenuItems itemId) {
        return mMenu.get(itemId.id);
    }

    public enum MenuItems {
        AVTO(0),
        SHOP(1),
        ABOUT(2),
        EXIT(3);

        public int id;
        MenuItems(int id) {
            this.id = id;
        }
    }
}
