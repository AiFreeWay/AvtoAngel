package upplic.com.angelavto.presentation.views.activities;


import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.models.Alarm;
import upplic.com.angelavto.presentation.adapters.LoginViewPagerAdapter;
import upplic.com.angelavto.presentation.receivers.SmsCodeReceiver;
import upplic.com.angelavto.presentation.utils.DrawerListener;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;


public class LoginActivity extends BaseActivity<AcLoginCtrl> {

    public static final int INPUT_PHONE_SLIDE_POSITION = 0;
    public static final int GET_CODE_SLIDE_POSITION = 1;

    public static final String API_KEY_TAG = "apikey";
    public static final String FIRTS_START = "first_start";
    public static final String ALARM_TAG = "alarm";
    private static final String SMS_RECEIVE_INTENT = "android.provider.Telephony.SMS_RECEIVED";

    @BindView(R.id.ac_login_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ac_login_vp_body)
    ViewPager mVpBody;
    @BindView(R.id.ac_login_root)
    ViewGroup mVgRoot;

    private MaterialMenuIconToolbar mMenuDrawer;
    private LoginViewPagerAdapter mAdapter;
    private String mNubmer;
    private Alarm mAlarm;
    private SmsCodeReceiver mCodeReceiver;

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == INPUT_PHONE_SLIDE_POSITION) {
                hideToolbar();
                disabledViewPageChange();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
        mAlarm = (Alarm) getIntent().getSerializableExtra(ALARM_TAG) ;
        mAdapter = new LoginViewPagerAdapter(getSupportFragmentManager());
        mViewController = new AcLoginCtrl(this);
        mCodeReceiver = new SmsCodeReceiver(mViewController::setCode);
        registerReceiver(mCodeReceiver, new IntentFilter(SMS_RECEIVE_INTENT));
        mVpBody.setAdapter(mAdapter);
        mVpBody.addOnPageChangeListener(mPageChangeListener);
        disabledViewPageChange();
        initToolbar();
        hideToolbar();
        mViewController.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            unregisterReceiver(mCodeReceiver);
        } catch (Exception e) {

        }
    }



    public void loadData(List<? extends Fragment> fragments) {
        mAdapter.loadData(fragments);
    }

    public void goToGetCodeSlide(String number) {
        mNubmer = number;
        enableViewPageChange();
        mViewController.reloadGetCodeFragment();
        mVpBody.setCurrentItem(GET_CODE_SLIDE_POSITION, true);
        showToolbar();
    }

    public void enableViewPageChange() {
        mAdapter.enableViewPageChange();
    }

    public void disabledViewPageChange() {
        mAdapter.disabledViewPageChange();
    }

    public void showToolbar() {
        mToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        mToolbar.setVisibility(View.INVISIBLE);
    }

    public String getNubmer() {
        return mNubmer;
    }

    public Alarm getAlarm() {
        return mAlarm;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationOnClickListener(v -> mVpBody.setCurrentItem(INPUT_PHONE_SLIDE_POSITION, true));
        mMenuDrawer = new MaterialMenuIconToolbar(this, ContextCompat.getColor(this, R.color.marengo), MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.ac_login_toolbar;
            }
        };
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }
}