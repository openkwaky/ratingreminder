package net.equasoft.ratingreminder.log;

import android.util.Log;

public class DebugLog {
    private static boolean debug = true;
    
    public static void d(String tag, String message){
        if (debug){
            Log.d(tag, message);
        }
    }

    public static void setDebug(boolean debug) {
        DebugLog.debug = debug;
    }
}
