package upplic.com.angelavto.presentation.utils;


import java.util.ArrayList;

import upplic.com.angelavto.presentation.views.fragments.AvtoFragment;
import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.views.fragments.ShopFragment;

public class FragmentsFactory {

    private ArrayList<BaseFragment> mFragmentsList;

    public FragmentsFactory() {
        mFragmentsList = new ArrayList<BaseFragment>();
        generateFragments();
    }

    private void generateFragments() {
        mFragmentsList.add(Fragments.SHOP.id, new ShopFragment());
    }

    public BaseFragment getFragment(Fragments fragmentIndefinder) {
        if (fragmentIndefinder == Fragments.AVTO)
            return new AvtoFragment();
        return mFragmentsList.get(fragmentIndefinder.id);
    }

    public enum Fragments {
        SHOP(0),
        AVTO(1);

        public int id;
        Fragments(int id) {
            this.id = id;
        }
    }
}