package com.example.tutorial;

import android.app.Application;

import com.onesignal.OneSignal;

public class ApplicationClass extends Application {
    private final String ONESIGNAL_APP_ID = "58482fcd-5f0a-47f3-8fe2-81968252abaa";
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.promptForPushNotifications();
    }
}
