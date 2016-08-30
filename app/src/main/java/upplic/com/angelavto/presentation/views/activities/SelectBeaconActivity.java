package upplic.com.angelavto.presentation.views.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.orhanobut.hawk.Hawk;
import com.rey.material.app.Dialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.view_controllers.AcSelectBeaconCtrl;


public class SelectBeaconActivity extends BaseActivity<AcSelectBeaconCtrl> {

    @BindView(R.id.ac_select_beacon_toolbar)
    Toolbar mToolbar;

    private MaterialMenuIconToolbar mMenuDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_select_beacon);
        ButterKnife.bind(this);
        initToolbar();
        mViewController = new AcSelectBeaconCtrl(this);
        mViewController.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            mViewController.popBack();
        return true;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().hide();
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setNavigationOnClickListener(v -> mViewController.popBack());
        mMenuDrawer = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.ac_select_beacon_toolbar;
            }
        };
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }


    public int getFragmentsBodyResId() {
        return R.id.ac_select_beacon_fl_fragments;
    }

    public FragmentRouter getRouter() {
        return  mViewController.getRouter();
    }
}
