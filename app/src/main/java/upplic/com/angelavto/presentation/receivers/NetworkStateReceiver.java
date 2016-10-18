package upplic.com.angelavto.presentation.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkStateReceiver extends BroadcastReceiver {

    private OnReceive mCallback;

    public NetworkStateReceiver(OnReceive callBack) {
        mCallback = callBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mCallback.execute(intent);
    }

    public static interface OnReceive {

        void execute(Intent intent);
    }
}
