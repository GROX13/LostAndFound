package com.steps.lostfound.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.steps.lostfound.R;
import com.steps.lostfound.activities.MainActivity;

/**
 * // TODO: Implementation needed!
 * Created by Giorgi on 7/10/2015.
 */
public class MatchedItemsAdapter extends BaseAdapter {

    private final Activity activity;

    public MatchedItemsAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return "Matched Item Placeholder!";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View row = inflater.inflate(R.layout.element_matched_item, parent, false);
        TextView name = (TextView) row.findViewById(R.id.matched_element_label);
        name.setText((String) getItem(position));
        return row;
    }
}
