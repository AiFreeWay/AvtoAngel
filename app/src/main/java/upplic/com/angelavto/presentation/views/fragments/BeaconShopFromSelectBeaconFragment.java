package upplic.com.angelavto.presentation.views.fragments;


import upplic.com.angelavto.R;

public class BeaconShopFromSelectBeaconFragment extends BeaconsShopFragment {

    @Override
    public void onStart() {
        super.onStart();
        try {
            getBaseActivity().getSupportActionBar().setTitle(R.string.buy_beacon);
            getBaseActivity().getSupportActionBar().show();
        } catch (Exception e) {

        }
    }
}
