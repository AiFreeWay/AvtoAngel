package upplic.com.angelavto.presentation.di.utils.material_menu_drawer;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.nineoldandroids.animation.Animator;

public abstract class MaterialMenuBaseSupport implements MaterialMenu {

    private static final String STATE_KEY = "material_menu_icon_state";
    private MaterialMenuDrawable.IconState currentState;
    private MaterialMenuDrawable drawable;

    public MaterialMenuBaseSupport(AppCompatActivity activity, int color, MaterialMenuDrawable.Stroke stroke) {
        this(activity, color, stroke, 800);
    }

    public MaterialMenuBaseSupport(AppCompatActivity activity, int color, MaterialMenuDrawable.Stroke stroke, int transformDuration) {
        this.currentState = MaterialMenuDrawable.IconState.BURGER;
        this.drawable = new MaterialMenuDrawable(activity, color, stroke, 1, transformDuration);
        this.setActionBarSettings(activity);
        if(this.providesActionBar()) {
            this.setupActionBar(activity);
        }

    }

    private void setupActionBar(AppCompatActivity activity) {
        View iconView = this.getActionBarHomeView(activity);
        View upView = this.getActionBarUpView(activity);
        if(iconView != null && upView != null) {
            ViewGroup.MarginLayoutParams iconParams = (ViewGroup.MarginLayoutParams)iconView.getLayoutParams();
            iconParams.bottomMargin = 0;
            iconParams.topMargin = 0;
            iconParams.leftMargin = 0;
            iconView.setLayoutParams(iconParams);
            ViewGroup.MarginLayoutParams upParams = (ViewGroup.MarginLayoutParams)upView.getLayoutParams();
            upParams.leftMargin = activity.getResources().getDimensionPixelSize(com.balysv.materialmenu.R.dimen.mm_up_arrow_margin);
            upParams.rightMargin = 0;
            upView.setLayoutParams(upParams);
        } else {
            throw new IllegalStateException("Could not find ActionBar views");
        }
    }

    protected abstract void setActionBarSettings(AppCompatActivity var1);

    protected abstract View getActionBarHomeView(AppCompatActivity var1);

    protected abstract View getActionBarUpView(AppCompatActivity var1);

    protected abstract boolean providesActionBar();

    public final void setState(MaterialMenuDrawable.IconState state) {
        this.currentState = state;
        this.getDrawable().setIconState(state);
    }

    public final MaterialMenuDrawable.IconState getState() {
        return this.getDrawable().getIconState();
    }

    public final void animateState(MaterialMenuDrawable.IconState state) {
        this.currentState = state;
        this.getDrawable().animateIconState(state);
    }

    public final void animatePressedState(MaterialMenuDrawable.IconState state) {
        this.animateState(state);
    }

    public final void setColor(int color) {
        this.getDrawable().setColor(color);
    }

    public final void setVisible(boolean visible) {
        this.getDrawable().setVisible(visible);
    }

    public final void setTransformationDuration(int duration) {
        this.getDrawable().setTransformationDuration(duration);
    }

    public final void setInterpolator(Interpolator interpolator) {
        this.getDrawable().setInterpolator(interpolator);
    }

    public final void setAnimationListener(Animator.AnimatorListener listener) {
        this.getDrawable().setAnimationListener(listener);
    }

    public final void setRTLEnabled(boolean rtlEnabled) {
        this.getDrawable().setRTLEnabled(rtlEnabled);
    }

    public final void setTransformationOffset(MaterialMenuDrawable.AnimationState animationState, float value) {
        this.currentState = this.getDrawable().setTransformationOffset(animationState, value);
    }

    public final MaterialMenuDrawable getDrawable() {
        return this.drawable;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString("material_menu_icon_state", this.currentState.name());
    }

    public void syncState(Bundle state) {
        if(state != null) {
            String iconStateName = state.getString("material_menu_icon_state");
            if(iconStateName == null) {
                iconStateName = MaterialMenuDrawable.IconState.BURGER.name();
            }

            this.setState(MaterialMenuDrawable.IconState.valueOf(iconStateName));
        }

    }
}

