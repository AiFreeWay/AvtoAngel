package upplic.com.angelavto.presentation.adapters;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.Collections;
import java.util.List;

public class ViewPagerTabsAdapter extends FragmentPagerAdapter {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<TabLayout.Tab> mTabs = Collections.emptyList();
    private List<Fragment> mFragments = Collections.emptyList();
    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    public ViewPagerTabsAdapter(FragmentManager fragmentManager, TabLayout tabLayout, ViewPager viewPager) {
        super(fragmentManager);
        mTabLayout = tabLayout;
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(tabSelectedListener);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    public void loadData(List<TabLayout.Tab> tabs, List<Fragment> fragments) {
        mTabs = tabs;
        mFragments = fragments;
        for (TabLayout.Tab tab : tabs)
            mTabLayout.addTab(tab);
        notifyDataSetChanged();
    }
}
