package com.jalilurrahman.camera_r_d;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Murtuza Rahman on 2019-04-29.
 */
public class ApplicationLoader extends Application {
    @SuppressLint("StaticFieldLeak")
    public static volatile Context applicationContext;

    public static volatile Handler applicationHandler;

    public static volatile boolean mainInterfacePaused = true;
    public static volatile boolean externalInterfacePaused = true;

    public ApplicationLoader() {
        super();
    }

    @Override
    public void onCreate() {
        try {
            applicationContext = getApplicationContext();
        } catch (Throwable ignore) {

        }

        super.onCreate();

        if (applicationContext == null) {
            applicationContext = getApplicationContext();
        }

        applicationHandler = new Handler(applicationContext.getMainLooper());
    }
}
