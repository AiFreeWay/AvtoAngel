package upplic.com.angelavto.presentation.utils;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;


public class DrawerListener implements DrawerLayout.DrawerListener {

    private MaterialMenuIconToolbar mMenuDrawer;

    public DrawerListener(MaterialMenuIconToolbar menuDrawer) {
        mMenuDrawer = menuDrawer;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mMenuDrawer.setTransformationOffset(MaterialMenuDrawable.AnimationState.ARROW_X, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
