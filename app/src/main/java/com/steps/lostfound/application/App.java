package com.steps.lostfound.application;

import android.app.Application;
import android.widget.BaseAdapter;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.PushService;
import com.steps.lostfound.activities.PushActivity;
import com.steps.lostfound.model.Observable;

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
//        ParseFacebookUtils.initialize(getApplicationContext());
//        ParsePush.subscribeInBackground("", new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
//                } else {
//                    Log.e("com.parse.push", "failed to subscribe for push", e);
//                }
//            }
//        });
//        PushService.setDefaultPushCallback(this, PushActivity.class);
        PushService.setDefaultPushCallback(this, PushActivity.class);
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
