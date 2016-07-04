package upplic.com.angelavto.presentation.di.views.activities;


import android.support.v7.app.AppCompatActivity;

import upplic.com.angelavto.presentation.di.app.AngelAvto;

public abstract class BaseActivity extends AppCompatActivity {

    public AngelAvto getAngelAvtoApplication() {
        return (AngelAvto) getApplication();
    }
}
