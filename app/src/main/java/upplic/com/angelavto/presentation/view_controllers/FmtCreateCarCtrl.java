package upplic.com.angelavto.presentation.view_controllers;


import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;

public class FmtCreateCarCtrl extends ViewController<CreateCarFragment> {


    public FmtCreateCarCtrl(CreateCarFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }
}
