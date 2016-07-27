package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.adapters.ViewPagerAdapter;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;

public class LoginActivity extends BaseActivity<AcLoginCtrl> {

    public static final int INPUT_PHONE_SLIDE_POSITION = 0;
    public static final int GET_CODE_SLIDE_POSITION = 1;

    @BindView(R.id.ac_login_vp_body)
    ViewPager mVpBody;

    private ViewPagerAdapter mAdapter;
    private String mNubmer;

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == INPUT_PHONE_SLIDE_POSITION)
                disabledViewPageChange();
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
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVpBody.setAdapter(mAdapter);
        mVpBody.addOnPageChangeListener(mPageChangeListener);
        disabledViewPageChange();
        mViewController = new AcLoginCtrl(this);
    }

    public void loadData(List<? extends Fragment> fragments) {
        mAdapter.loadData(fragments);
    }

    public void goToGetCodeSlide(String number) {
        mNubmer = number;
        enableViewPageChange();
        mVpBody.setCurrentItem(GET_CODE_SLIDE_POSITION, true);
    }

    public void enableViewPageChange() {
        mAdapter.enableViewPageChange();
    }

    public void disabledViewPageChange() {
        mAdapter.disabledViewPageChange();
    }

    public String getNubmer() {
        return mNubmer;
    }
}
