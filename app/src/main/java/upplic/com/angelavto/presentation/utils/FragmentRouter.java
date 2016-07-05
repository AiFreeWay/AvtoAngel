package upplic.com.angelavto.presentation.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentRouter {

    private final String BACK_STACK_TAG;

    private FragmentManager mFragmentManager;
    private int mViewId;

    private FragmentRouter(int viewId, FragmentManager fragmentManager) {
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

    private boolean isFragmentShowNow(Fragment fragment) {
        Fragment lastFragment = mFragmentManager.findFragmentById(mViewId);
        return lastFragment != null && mFragmentManager.findFragmentById(mViewId).getClass().isInstance(fragment);
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
