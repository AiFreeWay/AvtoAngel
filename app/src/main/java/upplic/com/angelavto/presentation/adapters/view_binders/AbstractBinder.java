package upplic.com.angelavto.presentation.adapters.view_binders;


import android.view.View;

public interface AbstractBinder<T> {

    public abstract View bind(View view, T data);
}
