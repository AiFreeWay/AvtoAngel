package upplic.com.angelavto.presentation.factories;

import android.os.Bundle;

import java.util.ArrayList;

import upplic.com.angelavto.presentation.views.fragments.AboutFragment;
import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;
import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.views.fragments.BeaconShopFromSelectBeaconFragment;
import upplic.com.angelavto.presentation.views.fragments.BeaconsShopFragment;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;
import upplic.com.angelavto.presentation.views.fragments.SelectBeaconFragment;


public class FragmentsFactory {

    private ArrayList<BaseFragment> mFragmentsList;

    public FragmentsFactory() {
        mFragmentsList = new ArrayList<BaseFragment>();
        generateFragments();
    }

    private void generateFragments() {
        mFragmentsList.add(Fragments.BEACONS_SHOP.id, new BeaconsShopFragment());
        mFragmentsList.add(Fragments.SELECT_BEACON.id, new SelectBeaconFragment());
        mFragmentsList.add(Fragments.CRAETE_CAR.id, new CreateCarFragment());
        mFragmentsList.add(Fragments.ABOUT.id, new AboutFragment());
        mFragmentsList.add(Fragments.BEACON_SHOP_FROM_SELECT_BEACON.id, new BeaconShopFromSelectBeaconFragment());

        for (BaseFragment fragment : mFragmentsList)
            addBundle(fragment);
    }

    public BaseFragment getFragment(Fragments fragmentIndefinder) {
        if (fragmentIndefinder.id == Fragments.AVTO.id) {
            BaseFragment fragment = new AvtoFragment();
            return addBundle(fragment);
        }
        return mFragmentsList.get(fragmentIndefinder.id);
    }

    public enum Fragments {
        BEACONS_SHOP(0),
        SELECT_BEACON(1),
        CRAETE_CAR(2),
        ABOUT(3),
        BEACON_SHOP_FROM_SELECT_BEACON(4),
        AVTO(5);

        public int id;
        Fragments(int id) {
            this.id = id;
        }
    }

    private BaseFragment addBundle(BaseFragment fragment) {
        fragment.setArguments(new Bundle());
        return fragment;
    }
}