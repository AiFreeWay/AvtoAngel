package upplic.com.angelavto.presentation.adapters.view_binders;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.presentation.view_controllers.FmtBeaconsCtrl;

public class BeaconBinder implements AbstractBinder<Beacon> {

    @BindView(R.id.v_beacon_im_image)
    ImageView mIvIcon;
    @BindView(R.id.v_beacon_tv_title)
    TextView mTvTitle;
    @BindView(R.id.v_beacon_tv_subtitle)
    TextView mTvSubtitle;
    @BindView(R.id.v_beacon_tv_description)
    TextView mTvDescription;


    private FmtBeaconsCtrl mViewController;
    private ListView mParent;
    private LayoutInflater mLayoutInflater;

    public BeaconBinder(FmtBeaconsCtrl controller) {
        mViewController = controller;
        mParent = mViewController.getRootView().getLvBeacons();
        mLayoutInflater = mViewController.getLayoutInflater();
    }

    @Override
    public View bind(View view, Beacon data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_beacon, mParent, false);
        ButterKnife.bind(this, view);

        mTvTitle.setText(data.getTitle());
        Picasso.with(view.getContext())
                .load(data.getImageUrl())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(mIvIcon);
        view.setOnClickListener(v -> mViewController.hundleProductItemClick(data));
        return view;
    }
}
