package upplic.com.angelavto.presentation.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.adapters.ViewPagerAdapter;
import upplic.com.angelavto.presentation.view_controllers.AcLoginCtrl;

public class LoginActivity extends BaseActivity<AcLoginCtrl> {

    private final int GET_CODE_SLIDE_POSITION = 1;
    private ViewPagerAdapter mAdapter;

    @BindView(R.id.ac_login_vp_body)
    ViewPager mVpBody;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVpBody.setAdapter(mAdapter);
        mVpBody.setOnTouchListener((view, event) -> true);
        mViewController = new AcLoginCtrl(this);
    }

    public void loadData(List<? extends Fragment> fragments) {
        mAdapter.loadData(fragments);
    }

    public void goToGetCodeSlide() {
        mVpBody.setCurrentItem(GET_CODE_SLIDE_POSITION, true);
    }
}
