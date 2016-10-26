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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.DriveCarInteractor;
import upplic.com.angelavto.domain.models.Alarm;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.presentation.di.components.DaggerServiceComponent;
import upplic.com.angelavto.presentation.di.modules.ServiceModule;
import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


public class PushNotificationReceiver extends FirebaseMessagingService {

    private static final int UPGRADING_NOFIFICATION = 0;
    private static final String TITLE_KEY = "title";
    private static final String CAR_ID_KEY = "carId";
    private static final String STATUS_KEY = "status";

    @Inject @Named(ServiceModule.DRIVE_CAR)
    DriveCarInteractor mDriveCarInteractor;
    @Inject @Named(ServiceModule.ALARM)
    AlarmInteractor mAlarmInteractor;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            checkNotificationAccess(remoteMessage);
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerServiceComponent.builder()
                .applicationComponent(getAngelAvtoApplication().getAppComponent())
                .serviceModule(new ServiceModule())
                .build()
                .inject(this);
    }

    private void checkNotificationAccess(RemoteMessage notiffication) {
        mDriveCarInteractor.getCarsOptions()
                .subscribeOn(Schedulers.newThread())
                .subscribe(carOptionses -> doOnGetCarOptions(carOptionses, notiffication),
                        e -> Log.e(AngelAvto.UNIVERSAL_LOG_TAG, "PushNotificationReceiver: checkNotificationAccess error "+e.toString()));
    }

    private void doOnGetCarOptions(List<CarOptions> carOptionses, RemoteMessage notiffication) {
        if (carOptionses.size() == 0)
            showNotification(notiffication);
        else {
            int carId = Integer.parseInt(notiffication.getData().get(CAR_ID_KEY));
            for (CarOptions carOptions : carOptionses) {
                if (carOptions.getId() == carId && carOptions.isNotification()) {
                    showNotification(notiffication);
                    return;
                }
            }
        }
    }

    private void showNotification(RemoteMessage notiffication) {
        mAlarmInteractor.insertAlarm(generateAlarm(notiffication))
                .subscribeOn(Schedulers.newThread())
                .subscribe(insertId -> {
                            NotificationCompat.Builder notificationBuilder = generateNotification(notiffication);
                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(UPGRADING_NOFIFICATION, notificationBuilder.build());},
                        e -> Log.e(AngelAvto.UNIVERSAL_LOG_TAG, "PushNotificationReceiver: writeAlarmToDB error "+e.toString()));
    }

    private NotificationCompat.Builder generateNotification(RemoteMessage notiffication) {
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String title = notiffication.getData().get(TITLE_KEY);
        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.warning))
                .setContentText(title)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(getActionIntent(notiffication));
    }

    private PendingIntent getActionIntent(RemoteMessage notiffication) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    private AngelAvto getAngelAvtoApplication() {
        return (AngelAvto) getApplication();
    }
}
