package upplic.com.angelavto.presentation.wrappers;


public abstract class AbstractHundleMemento<T> {

    protected T hundleObject;

    public AbstractHundleMemento() {
    }

    public AbstractHundleMemento(T hundleObject) {
        this.hundleObject = hundleObject;
    }

    public T getHundleObject() {
        return hundleObject;
    }

    public void setHundleObject(T hundleObject) {
        this.hundleObject = hundleObject;
    }
}
