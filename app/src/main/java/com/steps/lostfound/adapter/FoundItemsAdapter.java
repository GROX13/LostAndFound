package com.steps.lostfound.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.steps.lostfound.R;

/**
 * TODO: Implement!
 * Created by Giorgi on 7/10/2015.
 */
public class FoundItemsAdapter extends BaseAdapter {

    private final Activity activity;

    public FoundItemsAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return "Found Item Placeholder!";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.element_found_item, parent, false);
        TextView name = (TextView) row.findViewById(R.id.found_element_label);
        name.setText((String) getItem(position));
        return row;
    }
}
