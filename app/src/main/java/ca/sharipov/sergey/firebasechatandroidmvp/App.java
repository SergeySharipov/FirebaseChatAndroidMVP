package ca.sharipov.sergey.firebasechatandroidmvp;

import android.app.Application;

import ca.sharipov.sergey.firebasechatandroidmvp.data.AppSharedPrefHelper;
import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AppSharedPrefHelper.getInstance(this);
    }
}
