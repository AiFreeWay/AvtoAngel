package upplic.com.angelavto.presentation.views.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.adapters.MultyExListViewAdapter;
import upplic.com.angelavto.presentation.adapters.view_binders.AppMenuBinder;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.utils.DrawerListener;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.view_controllers.AcMainCtrl;

public class MainActivity extends BaseActivity<AcMainCtrl> {

    @BindView(R.id.ac_main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ac_main_dl_menu)
    DrawerLayout mDlMenu;
    @BindView(R.id.ac_main_fl_fragments)
    FrameLayout mFlFragmentsBody;
    @BindView(R.id.ac_main_lv_menu)
    ExpandableListView mElvMenu;

    private MaterialMenuIconToolbar mMenuDrawer;
    private MultyExListViewAdapter<AppMenuItem, AppMenuItem> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
        initToolbar();
        mViewController = new AcMainCtrl(this);
        mAdapter = new MultyExListViewAdapter<AppMenuItem, AppMenuItem>(new AppMenuBinder(mViewController));
        mElvMenu.addHeaderView(getHeaderView());
        mElvMenu.addFooterView(getFooterView(), null, true);
        mElvMenu.setAdapter(mAdapter);
        mViewController.start();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mMenuDrawer.syncState(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMenuDrawer.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            mViewController.popBack();
        return true;
    }

    public int getFragmentsBodyResId() {
        return R.id.ac_main_fl_fragments;
    }

    public void driveMenu() {
        if (mDlMenu.isDrawerOpen(Gravity.LEFT))
            mDlMenu.closeDrawer(Gravity.LEFT);
        else
            mDlMenu.openDrawer(Gravity.LEFT);
    }

    public ListView getLvMenu() {
        return mElvMenu;
    }

    public void loadData(List<AppMenuItem> menu) {
        mAdapter.loadData(menu);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setNavigationOnClickListener(v -> driveMenu());
        mMenuDrawer = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.ac_main_toolbar;
            }
        };
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
        mDlMenu.addDrawerListener(new DrawerListener(mMenuDrawer));
    }

    private View getHeaderView() {
        return mViewController.getLayoutInflater().inflate(R.layout.v_app_menu_header, mElvMenu, false);
    }

    private View getFooterView() {
        View footer = mViewController.getLayoutInflater().inflate(R.layout.v_app_menu_footer, mElvMenu, false);
        Button createCar = (Button) footer.findViewById(R.id.v_app_menu_footer_btn_create_car);
        createCar.setOnClickListener(v -> { mViewController.showFragmet(FragmentsFactory.Fragments.CRAETE_CAR);
            driveMenu();});
        return footer;
    }
}
