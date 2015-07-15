package com.steps.lostfound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.steps.lostfound.R;
import com.steps.lostfound.model.Item;

/**
 * Implements list view for lost items.
 * <p/>
 * Created by Giorgi on 7/10/2015.
 */
public class LostItemsAdapter extends ParseQueryAdapter<Item> {

    private LayoutInflater inflater;

    public LostItemsAdapter(Context context,
                            ParseQueryAdapter.QueryFactory<Item> queryFactory) {
        super(context, queryFactory);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getItemView(Item item, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.element_matched_item, parent, false);
            TextView name = (TextView) view.findViewById(R.id.matched_element_label);
            name.setText("Hello");
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return view;
    }

    private static class ViewHolder {
        TextView itemTitle;
    }

}


