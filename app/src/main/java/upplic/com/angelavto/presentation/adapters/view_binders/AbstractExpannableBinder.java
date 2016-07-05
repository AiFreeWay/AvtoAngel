package upplic.com.angelavto.presentation.adapters.view_binders;


import android.view.View;

import upplic.com.angelavto.presentation.adapters.Expannable;

public interface AbstractExpannableBinder<G extends Expannable<C>, C> extends AbstractBinder<G> {

    public abstract View bindChild(View view, C data);
}
