package upplic.com.angelavto.presentation.adapters.view_binders;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.factories.AppMenuFactory;
import upplic.com.angelavto.presentation.models.AppMenuItem;
import upplic.com.angelavto.presentation.view_controllers.AcMainCtrl;


public class AppMenuBinder implements AbstractExpannableBinder<AppMenuItem, AppMenuItem> {

    @BindView(R.id.v_app_menu_iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.v_app_menu_tv_title)
    TextView mTvTitle;
    @BindView(R.id.v_app_menu_iv_indicator)
    ImageView mIvIndicator;

    private AcMainCtrl mViewController;
    private ListView mParent;
    private LayoutInflater mLayoutInflater;

    public AppMenuBinder(AcMainCtrl controller) {
        mViewController = controller;
        mParent = mViewController.getRootView().getLvMenu();
        mLayoutInflater = mViewController.getLayoutInflater();
    }

    @Override
    public View bind(View view, AppMenuItem data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_app_menu, mParent, false);
        ButterKnife.bind(this, view);
        mIvIcon.setImageDrawable(getDrawable(data.getDrawable()));
        mTvTitle.setText(data.getTitle());
        if (data.getExpannableList().size() > 0) {
            mIvIndicator.setVisibility(View.VISIBLE);
            view.setOnClickListener(v -> mViewController.hundleExpandAppMenuClick());
        } else {
            view.setOnClickListener(v -> mViewController.hundleAppMenuClick(data));
            mIvIndicator.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @Override
    public View bindChild(View view, AppMenuItem data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_app_menu_child, mParent, false);
        ButterKnife.bind(this, view);
        mIvIcon.setImageDrawable(getDrawable(data.getDrawable()));
        mTvTitle.setText(data.getTitle());
        view.setOnClickListener(v -> mViewController.hundleAppMenuClick(data));
        return view;
    }

    private Drawable getDrawable(int res) {
        return ContextCompat.getDrawable(mParent.getContext(), res);
    }
}
