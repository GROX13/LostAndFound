package com.steps.lostfound.application;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.BaseAdapter;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.steps.lostfound.model.Observable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Application class that starts before everything and lasts
 * till application is alive.
 * <p/>
 * We use it for Parse initialization.
 * P.S Not only parse initialization.
 * <p/>
 * Created by ioane on 7/7/15.
 */
public class App extends Application implements Observable {
    private static App app;
    private List<BaseAdapter> adaptersObservables;

    public static Observable getDataObservable() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Save application for future usage
        App.app = this;
        adaptersObservables = new ArrayList<>();
        Parse.initialize(this, "TtYZcp6avRRiewOd3VkYUOAKsyByyzBOM57Eze5S",
                "tLYN4EgAKwhIR3umaDQWtIBc7yL0mS8EWEh5NaxG");
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        FacebookSdk.sdkInitialize(getApplicationContext());
        ParseFacebookUtils.initialize(getApplicationContext());
    }

    @Override
    public void registerAdapter(BaseAdapter adapter) {
        adaptersObservables.add(adapter);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < adaptersObservables.size(); i++)
            adaptersObservables.get(i).notifyDataSetChanged();
    }
}
