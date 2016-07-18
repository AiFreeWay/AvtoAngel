package upplic.com.angelavto.presentation.wrappers;


import android.os.Bundle;

import upplic.com.angelavto.presentation.utils.FragmentsFactory;

public class FragmentHandleMemento extends AbstractHundleMemento<FragmentsFactory.Fragments> {

    private Bundle fragmentArgs;

    public FragmentHandleMemento(FragmentsFactory.Fragments hundleObject , MenuHandlers hundlerType) {
        super(hundleObject, hundlerType);
    }

    public FragmentHandleMemento(FragmentsFactory.Fragments hundleObject, Bundle fragmentArgs, MenuHandlers hundlerType) {
        super(hundleObject, hundlerType);
        this.fragmentArgs = fragmentArgs;
    }

    public Bundle getFragmentArgs() {
        return fragmentArgs;
    }

    public void setFragmentArgs(Bundle fragmentArgs) {
        this.fragmentArgs = fragmentArgs;
    }
}
