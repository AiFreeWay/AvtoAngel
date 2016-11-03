package upplic.com.angelavto.presentation.utils;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NotificationBuffer {

    private static final int UPGRADING_NOFIFICATION = 0;

    private Notification mNotification;
    private boolean isFree = true;
    private Context mContext;

    public NotificationBuffer(Context context) {
        mContext = context;
    }

    public void push(Notification notification) {
        mNotification = notification;
        startAbsorbBuffer();
    }

    private void startAbsorbBuffer() {
        if (isFree) {
            isFree = false;
            Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(val -> {
                        showNotification(mNotification);
                        isFree = true;
                    }, e -> {});
        }
    }

    private void showNotification(Notification notification) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(UPGRADING_NOFIFICATION, notification);
    }
}
