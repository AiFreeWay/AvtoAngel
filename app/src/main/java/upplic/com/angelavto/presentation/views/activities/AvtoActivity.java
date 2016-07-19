package upplic.com.angelavto.presentation.views.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.adapters.ViewPagerAdapter;
import upplic.com.angelavto.presentation.view_controllers.AcAvtoCtrl;

public class AvtoActivity extends BaseActivity<AcAvtoCtrl> {

    public static final String CAR_ID = "carid";

    @BindView(R.id.ac_avto_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ac_avto_vp_body)
    ViewPager mVpBody;
    @BindView(R.id.ac_avto_tl_tabs)
    TabLayout mTlTabs;

    private int mCarId;
    private ViewPagerAdapter mAdapter;
    private MaterialMenuIconToolbar mMenuDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_auto);
        ButterKnife.bind(this);
        mCarId = getIntent().getIntExtra(CAR_ID, -1);
        initToolbar();
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTlTabs, mVpBody);
        mVpBody.setAdapter(mAdapter);
        mViewController = new AcAvtoCtrl(this);
        mViewController.start();
        setDangerState();
    }

    public int getCarId() {
        return mCarId;
    }

    public void loadData(List<TabLayout.Tab> tabs, List<Fragment> fragments) {
        mAdapter.loadData(tabs, fragments);
    }

    public TabLayout getTlTabs() {
        return mTlTabs;
    }

    public void setDangerState() {
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.marron));
        mTlTabs.setBackgroundColor(ContextCompat.getColor(this, R.color.marron));
    }

    public void setNormalState() {
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        mTlTabs.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setNavigationOnClickListener(v -> finish());
        mMenuDrawer = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.ac_avto_toolbar;
            }
        };
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.BURGER);
    }
}
