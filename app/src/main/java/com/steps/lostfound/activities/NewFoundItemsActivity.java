package com.steps.lostfound.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.steps.lostfound.R;
import com.steps.lostfound.utils.DatePickerFragment;

public class NewFoundItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_found_items);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        }
        final EditText dateLost = (EditText) findViewById(R.id.date);
        dateLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setDateView(dateLost);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        String[] categoryArray = getResources().getStringArray(R.array.category_array);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoryArray);
        Spinner category = (Spinner) findViewById(R.id.category_spinner);
        category.setAdapter(categoryAdapter);

    }

}
