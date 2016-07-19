package upplic.com.angelavto.presentation.wrappers;

import android.content.Intent;


public class ActivityHandleMemento extends AbstractHundleMemento<Intent> {

    public ActivityHandleMemento(Intent hundleObject, MenuHandlers hundlerType) {
        super(hundleObject, hundlerType);
    }
}