package upplic.com.angelavto.presentation.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;

import upplic.com.angelavto.R;
import upplic.com.angelavto.data.net_store.StandAloneNetworkController;


public class ServerPingerService extends IntentService {

    private static final String NAME = ServerPingerService.class.getCanonicalName();
    private static final int NOTIFICATION_ID = 1;
    private static final int LONG_DELAY_MILISECONDS = 60000;
    private static final int SHORT_DELAY_MILISECONDS = 8000;

    private final Uri NOTIFICATION_SOUND = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    private StandAloneNetworkController mNetworkController;

    public ServerPingerService() {
        super(NAME);
        mNetworkController = new StandAloneNetworkController();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopForeground(false);
        startPingSession();
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private void startPingSession() {
        new Thread(() -> {
            while (true) {
                boolean isServerPinged = pingServer();
                startForeground(NOTIFICATION_ID, getNotification(isServerPinged));
                sleep(LONG_DELAY_MILISECONDS);
            }
        }).start();
    }

    private boolean pingServer() {
        for (int i = 0; i < 6; i++) {
            try {
                if (mNetworkController.ping().execute().isSuccessful())
                    return true;
                else
                    sleep(SHORT_DELAY_MILISECONDS);
            } catch(IOException e) {
                sleep(SHORT_DELAY_MILISECONDS);
            }
        }
        return false;
    }

    private Notification getNotification(boolean connectionState) {
        if (connectionState)
            return getNotificationTemplate()
                    .setContentText("Подключено")
                    .build();
        return getNotificationTemplate()
                .setContentText("Проблемы с подключением")
                .setSound(NOTIFICATION_SOUND)
                .build();
    }

    private NotificationCompat.Builder getNotificationTemplate() {
        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alarm_icon)
                .setContentTitle("Состояние подключения к системе")
                .setAutoCancel(true);
    }

    private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

