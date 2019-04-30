package com.jalilurrahman.camera_r_d;

import android.content.SharedPreferences;

/**
 * Created by Murtuza Rahman on 2019-04-29.
 */
public class MessagesController {

    private SharedPreferences mainPreferences;

    private static volatile MessagesController[] Instance = new MessagesController[3];
    public static MessagesController getInstance(int num) {
        MessagesController localInstance = Instance[num];
        if (localInstance == null) {
            synchronized (MessagesController.class) {
                localInstance = Instance[num];
                if (localInstance == null) {
                    Instance[num] = localInstance = new MessagesController(num);
                }
            }
        }
        return localInstance;
    }

    public static SharedPreferences getGlobalMainSettings() {
        return getInstance(0).mainPreferences;
    }

    public MessagesController(int num) {

    }
}
