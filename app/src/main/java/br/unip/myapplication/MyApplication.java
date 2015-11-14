package br.unip.myapplication;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context sApplicationContext;

    public static Context getContext() {
        return sApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = super.getApplicationContext();
    }
}
