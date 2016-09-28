package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.rey.material.widget.ProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.presentation.adapters.ViewPagerTabsAdapter;
import upplic.com.angelavto.presentation.models.Alarm;
import upplic.com.angelavto.presentation.view_controllers.FmtAvtoCtrl;
import upplic.com.angelavto.presentation.views.activities.MainActivity;


public class AvtoFragment extends BaseFragment<FmtAvtoCtrl> {

    public static final String CAR_OPTIONS_TAG = "carrr";
    public static final String ALARM_TAG = "alarmcar";

    @BindView(R.id.fmt_avto_vp_body)
    ViewPager mVpBody;
    @BindView(R.id.fmt_avto_tl_tabs)
    TabLayout mTlTabs;
    @BindView(R.id.fmt_avto_pv_progress)
    ProgressView mPvProgress;

    private CarOptions mCarOptions;
    private Car mCar;
    private ViewPagerTabsAdapter mAdapter;
    private MainActivity mActivity;
    private Alarm mAlarm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_auto, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewController = new FmtAvtoCtrl(this);
        mActivity = (MainActivity) getActivity();
        mCarOptions  = (CarOptions) getArguments().getSerializable(CAR_OPTIONS_TAG);
        mAlarm  = (Alarm) getArguments().getSerializable(ALARM_TAG);
        mAdapter = new ViewPagerTabsAdapter(getChildFragmentManager(), mTlTabs, mVpBody);
        mVpBody.setAdapter(mAdapter);
        if (mAlarm != null)
            setDangerState();
        else
            setNormalState();
        mViewController.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getBaseActivity().getSupportActionBar().setTitle(mCarOptions.getTitle());
        } catch (Exception e) {

        }
    }

    @Override
    public void refresh() {
        super.refresh();
        mViewController.initCarDetail();
    }

    public void loadData(List<TabLayout.Tab> tabs, List<Fragment> fragments) {
        mAdapter.loadData(tabs, fragments);
    }

    public TabLayout getTlTabs() {
        return mTlTabs;
    }

    public void setDangerState() {
        mActivity.getToolbar().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.marron));
        mTlTabs.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.marron));
    }

    public void setNormalState() {
        mActivity.getToolbar().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mTlTabs.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }

    public void setCatDetail(Car car) {
        mCar = car;
    }

    public Car getCar() {
        return mCar;
    }

    public CarOptions getCarOptions() {
        return mCarOptions;
    }

    public void showStartLoad() {
        mPvProgress.start();
    }

    public void showStopLoad() {
        mPvProgress.stop();
    }
}
