package com.steps.lostfound.broadcastreceiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.steps.lostfound.R;
import com.steps.lostfound.activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ParseReceiver extends BroadcastReceiver {

    private static final String TAG = ParseReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(ParseReceiver.class.getSimpleName(), "Received some intent from Parse!");

        try {
            String action = intent.getAction();
            String channel = intent.getExtras().getString("com.parse.Channel");
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

            Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
            Iterator itr = json.keys();
            while (itr.hasNext()) {
                String key = (String) itr.next();
                Log.d(TAG, "..." + key + " => " + json.getString(key));
                // TODO: Implement!
            }
            createNotification(context);
        } catch (JSONException e) {
            Log.d(TAG, "JSONException: " + e.getMessage());
        }
    }

    private void createNotification(Context context) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Matches found")
                        .setContentText("On server some matches happened please " +
                                "check if your item was found!");

        Intent resultIntent = new Intent(context, MainActivity.class);

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);


    }

}
