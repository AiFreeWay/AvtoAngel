package upplic.com.angelavto.presentation.factories;

import android.os.Bundle;

import java.util.ArrayList;

import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.views.fragments.BeaconsFragment;
import upplic.com.angelavto.presentation.views.fragments.BeaconsShopFragment;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;
import upplic.com.angelavto.presentation.views.fragments.GarageFragment;
import upplic.com.angelavto.presentation.views.fragments.SelectBeaconFragment;


public class FragmentsFactory {

    private ArrayList<BaseFragment> mFragmentsList;

    public FragmentsFactory() {
        mFragmentsList = new ArrayList<BaseFragment>();
        generateFragments();
    }

    private void generateFragments() {
        mFragmentsList.add(Fragments.BEACONS.id, new BeaconsFragment());
        mFragmentsList.add(Fragments.BEACONS_SHOP.id, new BeaconsShopFragment());
        mFragmentsList.add(Fragments.SELECT_BEACON.id, new SelectBeaconFragment());
        mFragmentsList.add(Fragments.CRAETE_CAR.id, new CreateCarFragment());
        mFragmentsList.add(Fragments.GARAGE.id, new GarageFragment());

        for (BaseFragment fragment : mFragmentsList)
            addBundle(fragment);
    }

    public BaseFragment getFragment(Fragments fragmentIndefinder) {
        return mFragmentsList.get(fragmentIndefinder.id);
    }

    public enum Fragments {
        BEACONS(0),
        BEACONS_SHOP(1),
        SELECT_BEACON(2),
        CRAETE_CAR(3),
        GARAGE(4);

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