package upplic.com.angelavto.presentation.adapters.view_binders;


import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.view_controllers.FmtGarageCtrl;

public class GarageBinder implements AbstractBinder<Car> {

    @BindView(R.id.v_garage_tv_title)
    TextView mTvTitle;
    @BindView(R.id.v_garage_tv_description)
    TextView mTvDescription;
    @BindView(R.id.v_garage_iv_edit)
    ImageView mIvEdit;

    private FmtGarageCtrl mViewController;
    private ListView mParent;
    private LayoutInflater mLayoutInflater;

    public GarageBinder(FmtGarageCtrl controller) {
        mViewController = controller;
        mParent = mViewController.getRootView().getLvCars();
        mLayoutInflater = mViewController.getLayoutInflater();
    }

    @Override
    public View bind(View view, Car data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_garage, mParent, false);
        ButterKnife.bind(this, view);

        mTvTitle.setText(data.getTitle());
        mTvDescription.setText(parseStateToString(data.getState()));
        mIvEdit.setOnClickListener(v -> mViewController.openEditAvtoActivity(data));
        return view;
    }

    private String parseStateToString(int state) {
        String stateString;
        if (state == Car.STATE_LOCK) {
            stateString = "Защита включена";
            mTvDescription.setTextColor(ContextCompat.getColor(mViewController.getRootView().getContext(), R.color.green_jungle_krayola));
        } else {
            stateString = "Защита отключена";
            mTvDescription.setTextColor(ContextCompat.getColor(mViewController.getRootView().getContext(), R.color.marron));
        }
        return  stateString;
    }
}
