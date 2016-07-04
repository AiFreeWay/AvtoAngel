package upplic.com.angelavto.presentation.di.view_controllers;


public abstract class ViewController<V> {

    protected V mRootView;

    public ViewController(V view) {
        mRootView = view;
    }

    public abstract void start();
}
