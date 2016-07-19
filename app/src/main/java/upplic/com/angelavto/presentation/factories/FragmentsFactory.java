package upplic.com.angelavto.presentation.factories;

import android.os.Bundle;

import java.util.ArrayList;

import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;
import upplic.com.angelavto.presentation.views.fragments.ShopFragment;


public class FragmentsFactory {

    private ArrayList<BaseFragment> mFragmentsList;

    public FragmentsFactory() {
        mFragmentsList = new ArrayList<BaseFragment>();
        generateFragments();
    }

    private void generateFragments() {
        mFragmentsList.add(Fragments.SHOP.id, new ShopFragment());
        mFragmentsList.add(Fragments.CRAETE_CAR.id, new CreateCarFragment());
        for (BaseFragment fragment : mFragmentsList)
            addBundle(fragment);
    }

    public BaseFragment getFragment(Fragments fragmentIndefinder) {
        return mFragmentsList.get(fragmentIndefinder.id);
    }

    public enum Fragments {
        SHOP(0),
        CRAETE_CAR(1);

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