package upplic.com.angelavto.presentation.wrappers;

import rx.functions.Action0;


public class ActionHundleMemento extends AbstractHundleMemento<Action0> {

    public ActionHundleMemento(Action0 hundleObject, MenuHandlers hundlerType) {
        super(hundleObject, hundlerType);
    }
}
