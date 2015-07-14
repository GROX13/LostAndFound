package com.steps.lostfound.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Dialog fragment
 * Created by ioane on 7/12/15.
 */
public class DatePickerFragment extends DialogFragment{

    private DatePickerDialog.OnDateSetListener mDateSetListener;


    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener listener) {
        mDateSetListener = listener;
    }

    public DatePickerFragment() {
    }

    private TextView mDateView;

    public void setDateView(TextView mDateView) {
        this.mDateView = mDateView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), mDateSetListener, year, month, day);
    }
}