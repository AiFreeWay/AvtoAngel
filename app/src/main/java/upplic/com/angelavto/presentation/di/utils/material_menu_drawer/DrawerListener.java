package upplic.com.angelavto.presentation.di.utils.material_menu_drawer;


import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;

public class DrawerListener extends DrawerLayout.SimpleDrawerListener {

    private MaterialMenuBaseSupport mMenuDrawer;
    private boolean isDrawerOpened;

    public DrawerListener(MaterialMenuBaseSupport menuDrawer) {
        mMenuDrawer = menuDrawer;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mMenuDrawer.setTransformationOffset(MaterialMenuDrawable.AnimationState.BURGER_ARROW, isDrawerOpened ? 2 - slideOffset : slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        isDrawerOpened = true;
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        isDrawerOpened = false;
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        if(newState == DrawerLayout.STATE_IDLE) {
            if (isDrawerOpened)
                mMenuDrawer.setState(MaterialMenuDrawable.IconState.BURGER);
            else
                mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
        }
    }

    public void setDrawerOpened(boolean drawerOpened) {
        isDrawerOpened = drawerOpened;
    }
}
