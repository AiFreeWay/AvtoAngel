package upplic.com.angelavto.presentation.view_controllers;


public class ViewController<V> {

    protected V mRootView;

    public ViewController(V view) {
        mRootView = view;
    }

    public V getRootView() {
        return mRootView;
    }

    public void start() {

    };
}
