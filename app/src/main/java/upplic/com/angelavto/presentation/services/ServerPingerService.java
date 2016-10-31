package upplic.com.angelavto.presentation.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import upplic.com.angelavto.R;


public class ServerPingerService extends IntentService {

    private static final String NAME = ServerPingerService.class.getCanonicalName();
    private static final String SERVER_URI = "http://188.120.233.126/";
    private static final int NOTIFICATION_ID = 1;
    private static final int DELAY_MILISECONDS = 120000;

    private final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    private final Request PING_REQUEST;
    private final Uri NOTIFICATION_SOUND = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    public ServerPingerService() {
        super(NAME);
        PING_REQUEST = new Request.Builder()
                .url(SERVER_URI)
                .build();
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
            try {
                while (true) {
                    boolean isServerPinged =  pingServer();
                    startForeground(NOTIFICATION_ID, getNotification(isServerPinged));
                    Thread.sleep(DELAY_MILISECONDS);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean pingServer() {
        try {
            Call response = HTTP_CLIENT.newCall(PING_REQUEST);
            return response.execute().isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Состояние подключения к системе")
                .setAutoCancel(true);
    }
}
