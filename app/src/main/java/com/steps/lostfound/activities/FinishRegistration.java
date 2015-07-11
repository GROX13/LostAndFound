package com.steps.lostfound.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;
import com.steps.lostfound.R;

import org.w3c.dom.Text;

public class FinishRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_registration);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");

        final EditText emailView = (EditText)findViewById(R.id.email);
        final EditText nameView = (EditText)findViewById(R.id.full_name);
        final EditText phoneNumberView = (EditText)findViewById(R.id.phone_number);

        emailView.setText(email);
        nameView.setText(name);

        Button button = (Button)findViewById(R.id.finish_registration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailView.getText().toString();
                String name = nameView.getText().toString();
                String phoneNumber = phoneNumberView.getText().toString();

                ParseUser user = ParseUser.getCurrentUser();
                if(!TextUtils.isEmpty(email))
                   user.setEmail(email);
                if(!TextUtils.isEmpty(name))
                    user.put("name", name);
                if(!TextUtils.isEmpty(phoneNumber))
                    user.put("phone",phoneNumber);
                user.saveEventually();
                Intent i = new Intent(FinishRegistration.this,MainActivity.class);
                startActivity(i);
            }
        });
    }


}
