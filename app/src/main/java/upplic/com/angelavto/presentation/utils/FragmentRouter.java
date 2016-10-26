package upplic.com.angelavto.presentation.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import upplic.com.angelavto.AngelAvto;

public class FragmentRouter {

    private final String BACK_STACK_TAG;

    private FragmentManager mFragmentManager;
    private int mViewId;

    public FragmentRouter(int viewId, FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
        mViewId = viewId;
        BACK_STACK_TAG = viewId+"";
    }

    public void show(Fragment fragment) {
        if (!isFragmentShowNow(fragment)) {
            try {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(mViewId, fragment);
                transaction.addToBackStack(BACK_STACK_TAG);
                transaction.commitAllowingStateLoss();
            } catch (Exception e) {
                Logger.logError(e);
            }
        }
    }

    public boolean back() {
        boolean isCanBack = mFragmentManager.getBackStackEntryCount() > 1;
        if (isCanBack)
            mFragmentManager.popBackStack();
        return isCanBack;
    }

    public void setViewId(int id) {
        mViewId = id;
    }

    private boolean isFragmentShowNow(Fragment fragment) {
        Fragment lastFragment = mFragmentManager.findFragmentById(mViewId);
        if (lastFragment == null)
            return false;
        boolean isSameArguments = isSameArguments(fragment, lastFragment);
        return lastFragment.getClass().isInstance(fragment) && isSameArguments;
    }

    private boolean isSameArguments(Fragment fragment1, Fragment fragment2) {
        int arguments1HashCode = fragment1.getArguments().hashCode();
        int arguments2HashCode = fragment2.getArguments().hashCode();
        return arguments1HashCode == arguments2HashCode;
    }

    public static class RouterBilder {

        public FragmentRouter getRouter(int viewId, FragmentManager fragmentManager) {
            return new FragmentRouter(viewId, fragmentManager);
        }
    }
}
