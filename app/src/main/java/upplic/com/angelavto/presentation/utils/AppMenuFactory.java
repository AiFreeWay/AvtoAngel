package upplic.com.angelavto.presentation.utils;


import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.wrappers.AbstractHundleMemento;
import upplic.com.angelavto.presentation.wrappers.ActionHundleMemento;
import upplic.com.angelavto.presentation.wrappers.FragmentHandleMemento;

public class AppMenuFactory {

    private ArrayList<AppMenuItem> mMenu;

    public AppMenuFactory() {
        mMenu = new ArrayList<AppMenuItem>();
        generateItems();
    }

    private void generateItems() {
        mMenu.add(MenuItems.AVTO.id, new AppMenuItem("Автомобили", R.drawable.ic_auto, null));
        mMenu.add(MenuItems.SHOP.id, new AppMenuItem("Купить трекер", R.drawable.ic_shop, new FragmentHandleMemento(FragmentsFactory.Fragments.SHOP)));
        mMenu.add(MenuItems.ABOUT.id, new AppMenuItem("О программе", R.drawable.ic_about, new ActionHundleMemento(null)));
        mMenu.add(MenuItems.EXIT.id, new AppMenuItem("Выйти", R.drawable.ic_exit, new ActionHundleMemento(null)));
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
