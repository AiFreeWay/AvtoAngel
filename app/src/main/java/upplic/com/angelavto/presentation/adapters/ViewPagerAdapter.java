package upplic.com.angelavto.presentation.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Collections;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<? extends Fragment> mFragments = Collections.emptyList();

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void loadData(List<? extends Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }
}
