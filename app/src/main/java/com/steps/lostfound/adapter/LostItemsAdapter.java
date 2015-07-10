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
public class LostItemsAdapter extends BaseAdapter {

    private final Activity activity;

    public LostItemsAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return "Lost Item Placeholder!";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View row = inflater.inflate(R.layout.element_lost_item, parent, false);
        TextView name = (TextView) row.findViewById(R.id.lost_element_label);
        name.setText((String) getItem(position));
        return row;
    }
}
