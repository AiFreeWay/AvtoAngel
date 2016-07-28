package upplic.com.angelavto.presentation.adapters.view_binders;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.presentation.view_controllers.FmtBeaconsShopCtrl;

public class BeaconShopBinder implements AbstractBinder<Beacon> {

    @BindView(R.id.v_beacon_im_image)
    ImageView mIvIcon;
    @BindView(R.id.v_beacon_tv_title)
    TextView mTvTitle;
    @BindView(R.id.v_beacon_tv_subtitle)
    TextView mTvSubtitle;
    @BindView(R.id.v_beacon_tv_description)
    TextView mTvDescription;


    private FmtBeaconsShopCtrl mViewController;
    private ListView mParent;
    private LayoutInflater mLayoutInflater;

    public BeaconShopBinder(FmtBeaconsShopCtrl controller) {
        mViewController = controller;
        mParent = mViewController.getRootView().getLvProducts();
        mLayoutInflater = mViewController.getLayoutInflater();
    }

    @Override
    public View bind(View view, Beacon data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_beacon, mParent, false);
        ButterKnife.bind(this, view);

        mTvTitle.setText(data.getTitle());
        mTvSubtitle.setText(data.getSubtitle());
        mTvDescription.setText(data.getDescription());
        view.setOnClickListener(v -> mViewController.hundleProductItemClick(data));
        return view;
    }
}
