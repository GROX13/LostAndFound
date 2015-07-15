package com.steps.lostfound.application;

import android.app.Application;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;
import com.steps.lostfound.activities.MainActivity;
import com.steps.lostfound.model.Category;
import com.steps.lostfound.model.Item;
import com.steps.lostfound.model.User;

/**
 * Application class that starts before everything and lasts
 * till application is alive.
 * <p/>
 * We use it for Parse initialization.
 * P.S Not only parse initialization.
 * <p/>
 * Created by ioane on 7/7/15.
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Category.class);
        ParseObject.registerSubclass(Item.class);

        // Enable the local data store
        Parse.enableLocalDatastore(getApplicationContext());

        // Save application for future usage
        Parse.initialize(this, "TtYZcp6avRRiewOd3VkYUOAKsyByyzBOM57Eze5S",
                "tLYN4EgAKwhIR3umaDQWtIBc7yL0mS8EWEh5NaxG");

        // Set up our app to use Anonymous Users, so that people
        // can save todos locally without having to log in.
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        FacebookSdk.sdkInitialize(getApplicationContext());
        ParseFacebookUtils.initialize(getApplicationContext());

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground(
                new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.e(App.class.getSimpleName(),
                                "Installation object saved " + ((e != null) ? "failed" : "successfully"));
                    }
                }
        );
    }


}
