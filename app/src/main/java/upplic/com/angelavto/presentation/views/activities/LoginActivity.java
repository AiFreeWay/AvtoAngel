package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.adapters.LoginViewPagerAdapter;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;


public class LoginActivity extends BaseActivity<AcLoginCtrl> {

    public static final int GET_CODE_SLIDE_POSITION = 1;
    public static final String API_KEY_TAG = "apikey";

    @BindView(R.id.ac_login_vp_body)
    ViewPager mVpBody;

    private LoginViewPagerAdapter mAdapter;
    private String mNubmer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
        mAdapter = new LoginViewPagerAdapter(getSupportFragmentManager());
        mVpBody.setAdapter(mAdapter);
        mVpBody.setOnTouchListener((view, event) -> true);
        mViewController = new AcLoginCtrl(this);
        mViewController.start();
    }

    public void loadData(List<? extends Fragment> fragments) {
        mAdapter.loadData(fragments);
    }

    public void goToGetCodeSlide(String number) {
        mNubmer = number;
        mVpBody.setCurrentItem(GET_CODE_SLIDE_POSITION, true);
    }

    public String getNubmer() {
        return mNubmer;
    }
}
