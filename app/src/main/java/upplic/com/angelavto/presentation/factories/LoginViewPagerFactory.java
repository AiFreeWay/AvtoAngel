package upplic.com.angelavto.presentation.factories;

import java.util.ArrayList;
import java.util.List;

import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.views.fragments.GetCodeFragment;
import upplic.com.angelavto.presentation.views.fragments.InputPhoneFragment;


public class LoginViewPagerFactory {

    private ArrayList<BaseFragment> mFragmentsList;

    public LoginViewPagerFactory() {
        mFragmentsList = new ArrayList<BaseFragment>();
        generateFragments();
    }

    private void generateFragments() {
        mFragmentsList.add(0, new InputPhoneFragment());
        mFragmentsList.add(1, new GetCodeFragment());
    }

    public List<BaseFragment> getFragments() {
        return mFragmentsList;
    }
}
