package upplic.com.angelavto.presentation.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;



public class LocationListenerGoogleApiClient implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private DoOnConnected mDoOnConnected;

    public LocationListenerGoogleApiClient(DoOnConnected doOnConnected) {
        mDoOnConnected = doOnConnected;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mDoOnConnected.doOnConnected();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public interface DoOnConnected {

        void doOnConnected();
    }
}
