package upplic.com.angelavto.presentation.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

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
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(mViewId, fragment);
            transaction.addToBackStack(BACK_STACK_TAG);
            transaction.commit();
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
        boolean isDifferentArguments = isDifferentArguments(fragment, lastFragment);
        return lastFragment.getClass().isInstance(fragment) && !isDifferentArguments;
    }

    private boolean isDifferentArguments(Fragment fragment1, Fragment fragment2) {
        int arguments1HashCode = fragment1.getArguments().hashCode();
        int arguments2HashCode = fragment2.getArguments().hashCode();
        return arguments1HashCode != arguments2HashCode;
    }

    public static class RouterBilder {

        private int mLastViewId;
        private FragmentRouter mLastRouter;
        private FragmentManager mFragmentManager;

        public RouterBilder(FragmentManager fragmentManager) {
            mFragmentManager = fragmentManager;
        }

        public FragmentRouter getRouter(int viewId) {
            if (mLastViewId != viewId || mLastRouter == null) {
                mLastViewId = viewId;
                mLastRouter = new FragmentRouter(viewId, mFragmentManager);
            }
            return mLastRouter;
        }
    }
}
