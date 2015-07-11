package com.steps.lostfound.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;


/**
 * Simple Activity that decides at launch which activity to start.
 *
 * if user is logged in, get to his main activity.
 * if not request him login.
 *
 * Created by ioane on 7/8/15.
 */
public class DispatcherActivity extends Activity{

    static int LOGIN_REQUEST = 0;
    static int TARGET_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runDispatch();
    }

    @Override
    final protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(resultCode);
        if (requestCode == LOGIN_REQUEST && resultCode == RESULT_OK) {
            runDispatch();
        } else {
            finish();
        }
    }

    private void runDispatch() {
        if (ParseUser.getCurrentUser() != null) {
            startActivityForResult(new Intent(this, MainActivity.class), TARGET_REQUEST);
        } else {
            startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST);
        }
    }
}
