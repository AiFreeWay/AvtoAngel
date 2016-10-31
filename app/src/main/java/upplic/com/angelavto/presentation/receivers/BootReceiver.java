package upplic.com.angelavto.presentation.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import upplic.com.angelavto.presentation.services.ServerPingerService;


public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, ServerPingerService.class));
    }
}
