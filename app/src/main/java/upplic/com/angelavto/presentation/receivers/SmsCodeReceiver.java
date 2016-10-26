package upplic.com.angelavto.presentation.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.utils.Logger;


public class SmsCodeReceiver extends BroadcastReceiver {

    private static final String APPLICATION_SMS_PROVIDER = "SMS.RU";
    private OnCodeRecievListener mOnCodeReceiveListener;

    public SmsCodeReceiver(OnCodeRecievListener OnCodeRecievListener) {
        mOnCodeReceiveListener = OnCodeRecievListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mOnCodeReceiveListener.onReceive(getCodeFromIntent(intent));
    }

    private String getCodeFromIntent(Intent intent) {
        String code = "";
        try {
            SmsMessage currentMessage = getSmsMessage(intent);
            if (currentMessage.getDisplayOriginatingAddress().equals(APPLICATION_SMS_PROVIDER))
                code = currentMessage.getDisplayMessageBody();
        } catch (Exception e) {
            Logger.logError(e);
        }
        return code;
    }

    private SmsMessage getSmsMessage(Intent intent) {
        SmsMessage smsMessage = null;
        if (Build.VERSION.SDK_INT >= 19) {
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (int i = 0; i < msgs.length; i++)
                smsMessage = msgs[i];
        } else {
            Object pdus[] = (Object[]) intent.getExtras().get("pdus");
            for (int i = 0; i < pdus.length; i++)
                smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        return smsMessage;
    }

    public interface OnCodeRecievListener {

        void onReceive(String code);
    }
}
