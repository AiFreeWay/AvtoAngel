package upplic.com.angelavto.presentation.factories;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.presentation.views.fragments.AvtoDriveFragment;
import upplic.com.angelavto.presentation.views.fragments.MapFragement;

public class AvtoViewPagerFactory {
    private List<TabLayout.Tab> mTabs;
    private List<Fragment> mFragments;

    private AvtoViewPagerFactory(TabLayout tabLayout) {
        mTabs = new ArrayList<TabLayout.Tab>();
        mFragments = new ArrayList<Fragment>();
        getnerateTabs(tabLayout);
        geeenrateFragments();
    }

    public List<TabLayout.Tab> getTabs() {
        return mTabs;
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    private void getnerateTabs(TabLayout tabLayout) {
        mTabs.add(0, tabLayout.newTab().setText("Состояние"));
        mTabs.add(1, tabLayout.newTab().setText("Карта"));
    }

    private void geeenrateFragments() {
        mFragments.add(0, new AvtoDriveFragment());
        mFragments.add(1, new MapFragement());
    }

    public static class Builder {

        public AvtoViewPagerFactory build(TabLayout tabLayout) {
            return new AvtoViewPagerFactory(tabLayout);
        }
    }
}
