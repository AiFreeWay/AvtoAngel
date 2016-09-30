package upplic.com.angelavto.presentation.services;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.models.Alarm;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class PushNotificationReceiver extends FirebaseMessagingService {

    private static final int UPGRADING_NOFIFICATION = 0;
    private static final String TITLE_KEY = "title";
    private static final String CAR_ID_KEY = "carId";
    private static final String STATUS_KEY = "status";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            showNotification(remoteMessage);
        } catch (Exception e) {
            Log.d(AngelAvto.UNIVERSAL_ERROR_TAG, "onMessageReceived error: "+e.toString());
        }
    }


    private void showNotification(RemoteMessage notiffication) {
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String title = notiffication.getData().get(TITLE_KEY);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.warning))
                .setContentText(title)
                .setAutoCancel(false)
                .setSound(defaultSoundUri)
                .setContentIntent(getActionIntent(notiffication));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(UPGRADING_NOFIFICATION, notificationBuilder.build());
    }

    private PendingIntent getActionIntent(RemoteMessage notiffication) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(LoginActivity.ALARM_TAG, generateAlarm(notiffication));
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
    }

    private Alarm generateAlarm(RemoteMessage notiffication) {
        Alarm alarm = new Alarm();
        alarm.setTitle(notiffication.getData().get(TITLE_KEY));
        alarm.setCarId(Integer.parseInt(notiffication.getData().get(CAR_ID_KEY)));
        int status = Integer.parseInt(notiffication.getData().get(STATUS_KEY));

        if (status == Alarm.AlarmTypes.NOT_SIGNAL.id)
            alarm.setStatus(Alarm.AlarmTypes.NOT_SIGNAL);
        else
            alarm.setStatus(Alarm.AlarmTypes.CAR_MOVES);
        return alarm;
    }
}
