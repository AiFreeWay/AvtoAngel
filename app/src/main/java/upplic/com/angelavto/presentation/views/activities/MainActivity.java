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
import com.orhanobut.hawk.Hawk;
import com.rey.material.app.Dialog;

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
    private Dialog mRestartDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
        initToolbar();
        mRestartDialog = new Dialog(this, R.style.login_dialog)
                .title(R.string.token_bad)
                .titleColor(ContextCompat.getColor(this, R.color.slate_gray))
                .positiveActionTextColor(ContextCompat.getColor(this, R.color.green_jungle_krayola))
                .cancelable(false)
                .positiveAction(R.string.exit)
                .positiveActionClickListener(v -> {
                    Hawk.remove(LoginActivity.API_KEY_TAG);
                    Hawk.remove(LoginActivity.FIRTS_START);
                    this.finish();});
        mViewController = new AcMainCtrl(this);
        mAdapter = new MultyExListViewAdapter<AppMenuItem, AppMenuItem>(new AppMenuBinder(mViewController));
        mElvMenu.addHeaderView(getHeaderView());
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

    @Override
    public void refresh() {
        super.refresh();
        mViewController.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewController.stop();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
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

    public boolean isDrawerOpen() {
        return mDlMenu.isDrawerOpen(Gravity.LEFT);
    }

    public ExpandableListView getLvMenu() {
        return mElvMenu;
    }

    public MultyExListViewAdapter<AppMenuItem, AppMenuItem> getAdapter() {
        return mAdapter;
    }

    public void loadData(List<AppMenuItem> menu) {
        mAdapter.loadData(menu);
    }

    public List<AppMenuItem> getData() {
        return mAdapter.getData();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void showDialog() {
        mRestartDialog.show();
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
}
