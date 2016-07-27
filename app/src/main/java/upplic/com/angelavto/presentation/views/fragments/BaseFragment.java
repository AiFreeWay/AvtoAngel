package upplic.com.angelavto.presentation.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import upplic.com.angelavto.presentation.di.components.ActivityComponent;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;


public abstract class BaseFragment<VC> extends Fragment {

    protected VC mViewController;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }


    public ActivityComponent getActivityComponent() {
        return getBaseActivity().getActivityComponent();
    }
}
