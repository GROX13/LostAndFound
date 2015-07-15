package com.steps.lostfound.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseGeoPoint;
import com.steps.lostfound.R;
import com.steps.lostfound.model.Item;
import com.steps.lostfound.utils.DatePickerFragment;

import java.util.Calendar;
import java.util.Date;

public class NewLostItemActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final int ACTION_PICK_PLACE = 10;
    private EditText mPickPlace;
    private EditText mDateEditText;
    private Spinner mCategorySpinner;
    private TextInputLayout mDateWrap;
    private TextInputLayout mPickPlaceWrap;

    private double mLatitude;
    private double mLongitude;
    private int mRadius;
    private Date mDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lost_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        }
        mDateWrap = (TextInputLayout)findViewById(R.id.date_wrap);
        mPickPlaceWrap = (TextInputLayout)findViewById(R.id.pick_place_wrap);
        mDateEditText = (EditText) findViewById(R.id.date);
        mDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setOnDateSetListener(NewLostItemActivity.this);
                newFragment.setDateView(mDateEditText);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        String[] categoryArray = getResources().getStringArray(R.array.category_array);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoryArray);
        mCategorySpinner = (Spinner) findViewById(R.id.category_spinner);
        mCategorySpinner.setAdapter(categoryAdapter);


        mPickPlace = (EditText) findViewById(R.id.pick_place);
        mPickPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewLostItemActivity.this, MapActivity.class);
                startActivityForResult(i, ACTION_PICK_PLACE);
            }
        });

        Button pushForm = (Button) findViewById(R.id.push);
        pushForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Toast.makeText(getBaseContext(), "Now we save", Toast.LENGTH_SHORT).show();
                    Item item = new Item();
                    item.setCategory((String)mCategorySpinner.getSelectedItem());
                    item.setLost(true);
                    item.setResolved(false);
                    item.setRadius(mRadius);
                    item.setGeoLocation(new ParseGeoPoint(mLatitude,mLongitude));
                    item.setDate(mDate);
                    item.saveEventually();
                }
            }
        });



    }

    private boolean validateFields() {
        boolean isValid = true;
        if (mRadius < 0) {
            isValid = false;
            mPickPlaceWrap.setError(getString(R.string.pick_place));
        }
        if (mDate == null) {
            isValid = false;
            mDateWrap.setError(getString(R.string.pick_date));
        }

        return isValid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_PICK_PLACE) {
            if (data != null && resultCode == RESULT_OK) {
                mLatitude = data.getDoubleExtra(MapActivity.LATITUDE, -1);
                mLongitude = data.getDoubleExtra(MapActivity.LONGITUDE, -1);
                mRadius = data.getIntExtra(MapActivity.RADIUS, -1);
                mPickPlace.setText(placeToStr());
            }
        }
    }

    private String placeToStr() {
        return "lat : " + mLatitude + " lon : " + mLongitude + " radius : " + mRadius;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("lat", mLatitude);
        outState.putDouble("long", mLongitude);
        outState.putInt("rad", mRadius);
        outState.putSerializable("date", mDate);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLatitude = savedInstanceState.getDouble("lat", -1);
        mLongitude = savedInstanceState.getDouble("long", -1);
        mRadius = savedInstanceState.getInt("rad", -1);
        mDate = (Date) savedInstanceState.getSerializable("date");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDateEditText.setText(String.format("%d/%d/%d", year, monthOfYear, dayOfMonth));
        Calendar cal = Calendar.getInstance();
        cal.set(year, monthOfYear, dayOfMonth);
        mDate = cal.getTime();
    }
}
