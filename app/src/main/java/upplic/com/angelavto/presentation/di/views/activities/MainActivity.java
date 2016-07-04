package upplic.com.angelavto.presentation.di.views.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;

import com.balysv.materialmenu.MaterialMenuBase;
import com.balysv.materialmenu.MaterialMenuDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.di.utils.material_menu_drawer.DrawerListener;
import upplic.com.angelavto.presentation.di.utils.material_menu_drawer.MaterialMenuBaseSupport;
import upplic.com.angelavto.presentation.di.utils.material_menu_drawer.MaterialMenuDrawer;
import upplic.com.angelavto.presentation.di.view_controllers.AcMainCtrl;

public class MainActivity extends BaseActivity<AcMainCtrl> {

    @BindView(R.id.ac_main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ac_main_dl_menu)
    DrawerLayout mDlMenu;

    private MaterialMenuBaseSupport mMenuDrawer;
    private DrawerListener mDrawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
        initToolbar();
        mViewController = new AcMainCtrl(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        try {
            mDrawerListener.setDrawerOpened(mDlMenu.isDrawerOpen(Gravity.LEFT));
            mMenuDrawer.syncState(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            mViewController.popBack();
        return true;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mMenuDrawer = new MaterialMenuDrawer(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN, mToolbar);
        mDrawerListener = new DrawerListener(mMenuDrawer);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }
}
