package upplic.com.angelavto.presentation.wrappers;


public abstract class AbstractHundleMemento<T> {

    public final MenuHandlers mHundlerType;
    protected T hundleObject;

    public AbstractHundleMemento(MenuHandlers hundlerType) {
        mHundlerType = hundlerType;
    }

    public AbstractHundleMemento(T hundleObject, MenuHandlers hundlerType) {
        this.hundleObject = hundleObject;
        mHundlerType = hundlerType;
    }

    public T getHundleObject() {
        return hundleObject;
    }

    public void setHundleObject(T hundleObject) {
        this.hundleObject = hundleObject;
    }

    public enum MenuHandlers {
        ACTION(0),
        FRAGMENT(1),
        ACTIVITY(2);
        public int id;

        MenuHandlers(int id) {
            this.id = id;
        }
    }
}
