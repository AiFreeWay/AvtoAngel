package upplic.com.angelavto.presentation.utils;


import java.util.ArrayList;

import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.views.fragments.ShopFragment;

public class FragmentsFactory {

    private ArrayList<BaseFragment> mFragmentsLIst;

    public FragmentsFactory() {
        mFragmentsLIst = new ArrayList<BaseFragment>();
        generateFragments();
    }

    private void generateFragments() {
        mFragmentsLIst.add(Fragments.SHOP.id, new ShopFragment());

    }

    public BaseFragment getFragment(Fragments fragmentIndefinder) {
        return mFragmentsLIst.get(fragmentIndefinder.id);
    }

    public enum Fragments {
        SHOP(0);

        public int id;
        Fragments(int id) {
            this.id = id;
        }
    }
}