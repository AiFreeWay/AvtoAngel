package upplic.com.angelavto.presentation.view_controllers;


public abstract class ViewController<V> {

    protected V mRootView;

    public ViewController(V view) {
        mRootView = view;
    }

    public V getRootView() {
        return mRootView;
    }

    public abstract void start();
}
