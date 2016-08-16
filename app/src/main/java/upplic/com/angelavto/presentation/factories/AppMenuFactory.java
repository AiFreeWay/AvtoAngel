package upplic.com.angelavto.presentation.factories;


import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.orhanobut.hawk.Hawk;
import com.rey.material.app.Dialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;
import upplic.com.angelavto.presentation.wrappers.AbstractHundleMemento;
import upplic.com.angelavto.presentation.wrappers.ActionHundleMemento;
import upplic.com.angelavto.presentation.wrappers.FragmentHandleMemento;

public class AppMenuFactory {

    private ArrayList<AppMenuItem> mMenu;
    private Dialog mExitDialog;
    private BaseActivity mActivity;

    public AppMenuFactory(BaseActivity activity) {
        mMenu = new ArrayList<AppMenuItem>();
        mActivity = activity;
        generateItems();
    }

    private void generateItems() {
        mMenu.add(MenuItems.AVTO.id, new AppMenuItem("Автомобили", R.drawable.ic_auto, new FragmentHandleMemento(FragmentsFactory.Fragments.CRAETE_CAR, AbstractHundleMemento.MenuHandlers.FRAGMENT)));
        mMenu.add(MenuItems.SHOP.id, new AppMenuItem("Купить трекер", R.drawable.ic_shop, new FragmentHandleMemento(FragmentsFactory.Fragments.BEACONS_SHOP, AbstractHundleMemento.MenuHandlers.FRAGMENT)));
        mMenu.add(MenuItems.ABOUT.id, new AppMenuItem("О программе", R.drawable.ic_about, new ActionHundleMemento(null, AbstractHundleMemento.MenuHandlers.ACTION)));
        mMenu.add(MenuItems.EXIT.id, new AppMenuItem("Выйти", R.drawable.ic_exit, new ActionHundleMemento(() -> getMaterialDialog().show(), AbstractHundleMemento.MenuHandlers.ACTION)));
    }

    private Dialog getMaterialDialog() {
        if (mExitDialog == null)
            mExitDialog = new Dialog(mActivity, R.style.login_dialog)
                    .title(R.string.want_exit)
                    .titleColor(ContextCompat.getColor(mActivity, R.color.slate_gray))
                    .actionTextColor(ContextCompat.getColor(mActivity, R.color.green_jungle_krayola))
                    .positiveAction(R.string.yes)
                    .negativeAction(R.string.no)
                    .positiveActionClickListener(v -> {
                        Hawk.remove(LoginActivity.API_KEY_TAG);
                        Hawk.remove(LoginActivity.FIRTS_START);
                        mActivity.finish();})
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
        ABOUT(2),
        EXIT(3);

        public int id;
        MenuItems(int id) {
            this.id = id;
        }
    }
}
