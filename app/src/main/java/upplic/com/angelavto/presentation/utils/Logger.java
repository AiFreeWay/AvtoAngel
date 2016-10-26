package upplic.com.angelavto.presentation.utils;

import android.util.Log;

import upplic.com.angelavto.AngelAvto;

public class Logger {

    private static final int TOP_STACK_TRACE = 0;

    public static void logError(Throwable e) {
        String logRecord = getExceptionPlace(e);
        logRecord += " error: "+e.getMessage();
        Log.e(AngelAvto.UNIVERSAL_LOG_TAG, logRecord);
    }

    private static String getExceptionPlace(Throwable e) {
        StackTraceElement topstackTraceElement = e.getStackTrace()[TOP_STACK_TRACE];
        return topstackTraceElement.getClassName()+" "+topstackTraceElement.getMethodName()+" on line "+topstackTraceElement.getLineNumber();
    }
}
