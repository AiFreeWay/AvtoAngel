package upplic.com.angelavto.presentation.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class LoginViewPagerAdapter extends FragmentPagerAdapter {

    private final int LOGIN_PAGE_COUNT = 2;

    private List<? extends Fragment> mFragments = Collections.emptyList();
    private AdapterPageSizer mAdapterPageSizer;

    public LoginViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mAdapterPageSizer = new AdapterPageSizer(mFragments.size());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        int position = mFragments.indexOf(object);
        return position == -1 ? POSITION_NONE : position;
    }

    @Override
    public int getCount() {
        return mAdapterPageSizer.getSize();
    }

    public void loadData(List<? extends Fragment> fragments) {
        mFragments = fragments;
        mAdapterPageSizer.setSize(mFragments.size());
        notifyDataSetChanged();
    }

    public void enableViewPageChange() {
        mAdapterPageSizer.canChange(true);
        notifyDataSetChanged();
    }

    public void disabledViewPageChange() {
        mAdapterPageSizer.canChange(false);
        if (mFragments.size() == LOGIN_PAGE_COUNT) {
            List<? extends Fragment> fullFragmentsList = mFragments.subList(0, mFragments.size());
            mFragments = mFragments.subList(0, 1);
            notifyDataSetChanged();
            mFragments = fullFragmentsList;
        }
    }

    private class AdapterPageSizer {

        private boolean mCanCnage = true;
        private int mSize;

        public AdapterPageSizer(int size) {
            mSize = size;
        }

        public void setSize(int size) {
            mSize = size;
        }

        public int getSize() {
            if (mCanCnage)
                return mSize;
            else
                return 1;
        }

        public void canChange(boolean canCnage) {
            mCanCnage = canCnage;
        }
    }
}
