package upplic.com.angelavto.presentation.di.utils.material_menu_drawer;

import android.annotation.TargetApi;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;

import upplic.com.angelavto.R;


public class MaterialMenuDrawer extends MaterialMenuBaseSupport {


    public MaterialMenuDrawer(AppCompatActivity activity, int color, MaterialMenuDrawable.Stroke stroke, Toolbar toolbar) {
        super(activity, color, stroke);
    }

    public MaterialMenuDrawer(AppCompatActivity activity, int color, MaterialMenuDrawable.Stroke stroke, int transformDuration, Toolbar toolbar) {
        super(activity, color, stroke, transformDuration);
    }

    protected View getActionBarHomeView(AppCompatActivity activity) {
        Log.d("++++", "MaterialMenuDrawer: getActionBarHomeView");
        Log.d("++++", "MaterialMenuDrawer: getActionBarHomeView "+getCustomView(activity).findViewById(android.R.id.home));
        return getCustomView(activity).findViewById(android.R.id.home);
    }

    protected View getActionBarUpView(AppCompatActivity activity) {
        Log.d("++++", "MaterialMenuDrawer: getActionBarUpView");
        Log.d("++++", "MaterialMenuDrawer: getActionBarUpView "+getActionBarHomeView(activity).findViewById(R.id.up));
        return getActionBarHomeView(activity).findViewById(R.id.up);
    }

    protected boolean providesActionBar() {
        return true;
    }

    @TargetApi(14)
    protected void setActionBarSettings(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setIcon(this.getDrawable());
    }

    private View getCustomView(AppCompatActivity activity) {
        return activity.getSupportActionBar().getCustomView();
    }
}
