package upplic.com.angelavto.presentation.factories;


import android.app.Activity;

import com.rey.material.app.Dialog;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.wrappers.AbstractHundleMemento;
import upplic.com.angelavto.presentation.wrappers.ActionHundleMemento;
import upplic.com.angelavto.presentation.wrappers.FragmentHandleMemento;

public class AppMenuFactory {

    private ArrayList<AppMenuItem> mMenu;
    private Dialog mExitDialog;
    private Activity mActivity;

    public AppMenuFactory(Activity activity) {
        mMenu = new ArrayList<AppMenuItem>();
        mActivity = activity;
        generateItems();
    }

    private void generateItems() {
        mMenu.add(MenuItems.AVTO.id, new AppMenuItem("Автомобили", R.drawable.ic_auto, null));
        mMenu.add(MenuItems.SHOP.id, new AppMenuItem("Купить трекер", R.drawable.ic_shop, new FragmentHandleMemento(FragmentsFactory.Fragments.BEACONS_SHOP, AbstractHundleMemento.MenuHandlers.FRAGMENT)));
        mMenu.add(MenuItems.GARAGE.id, new AppMenuItem("Гараж", R.drawable.ic_garage, new FragmentHandleMemento(FragmentsFactory.Fragments.GARAGE, AbstractHundleMemento.MenuHandlers.FRAGMENT)));
        mMenu.add(MenuItems.ABOUT.id, new AppMenuItem("О программе", R.drawable.ic_about, new ActionHundleMemento(null, AbstractHundleMemento.MenuHandlers.ACTION)));
        mMenu.add(MenuItems.EXIT.id, new AppMenuItem("Выйти", R.drawable.ic_exit, new ActionHundleMemento(() -> getMaterialDialog().show(), AbstractHundleMemento.MenuHandlers.ACTION)));
    }

    private Dialog getMaterialDialog() {
        if (mExitDialog == null)
            mExitDialog = new Dialog(mActivity, R.style.login_dialog)
                    .title(R.string.want_exit)
                    .positiveAction(R.string.yes)
                    .negativeAction(R.string.no)
                    .positiveActionClickListener(v -> mActivity.finish())
                    .negativeActionClickListener(v -> mExitDialog.dismiss());
        return mExitDialog;
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
        GARAGE(2),
        ABOUT(3),
        EXIT(4);

        public int id;
        MenuItems(int id) {
            this.id = id;
        }
    }
}
