package net.equasoft.ratingreminder.io;

import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefsTools {

    public static final String TAG = "PrefsTools";
    public static final String PREFS_NAME = "RatingReminderPrefs";

    public static final String PREFS_START_COUNT = "start_count";
    public static final String PREFS_LIMIT = "limit";
    public static final String PREFS_RATED = "rated";

    public static boolean getBool(Context ctx, String key) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(key, false);
    }

    public static void setBool(Context ctx, String key, boolean val) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, val);
        editor.commit();
    }

    public static float getFloat(Context ctx, String key) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        return settings.getFloat(key, 0);
    }

    public static void setFloat(Context ctx, String key, float val) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, val);
        editor.commit();
    }

    public static int getInteger(Context ctx, String key) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(key, 0);
    }

    public static void setInteger(Context ctx, String key, int val) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, val);
        editor.commit();
    }

    public static String getString(Context ctx, String key) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(key, null);
    }

    public static void setString(Context ctx, String key, String val) {

        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, val);
        editor.commit();
    }

    public static boolean hasKey(Context ctx, String key) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        return settings.contains(key);
    }

    public static void showPrefsValues(Context ctx) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);

        Map<String, ?> map = settings.getAll();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Log.d(TAG, "key : " + key);
            Log.d(TAG, "value : " + map.get(key));
        }
    }
    
    public static void clearPrefs(Context ctx){
        ctx.getSharedPreferences(PREFS_NAME, 0).edit().clear().commit();
    }
}