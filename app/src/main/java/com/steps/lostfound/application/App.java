package com.steps.lostfound.application;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Application class that starts before everything and lasts
 * till application is alive.
 * <p/>
 * We use it for Parse initialization.
 * P.S Not only parse initialization.
 * <p/>
 * Created by ioane on 7/7/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "TtYZcp6avRRiewOd3VkYUOAKsyByyzBOM57Eze5S",
                "tLYN4EgAKwhIR3umaDQWtIBc7yL0mS8EWEh5NaxG");
        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        ParseFacebookUtils.initialize(getApplicationContext());
    }

}
