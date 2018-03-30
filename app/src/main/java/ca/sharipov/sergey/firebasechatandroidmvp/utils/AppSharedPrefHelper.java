package ca.sharipov.sergey.firebasechatandroidmvp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.SHARED_PREFERENCES;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.SHARED_PREFERENCES_CURRENT_USER_ID;

public class AppSharedPrefHelper {

    private static AppSharedPrefHelper instance;

    private static SharedPreferences preferences;

    private AppSharedPrefHelper(Context context) {
        preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Nullable
    public static synchronized AppSharedPrefHelper getInstance(Context context) {
        if (instance == null && context != null) {
            instance = new AppSharedPrefHelper(context.getApplicationContext());
        }
        return instance;
    }

    static public void putCurrentUserId(String id) {
        preferences.edit().putString(SHARED_PREFERENCES_CURRENT_USER_ID, id).apply();
    }

    static public String getCurrentUserId() {
        return preferences.getString(SHARED_PREFERENCES_CURRENT_USER_ID, null);
    }

}
