package com.steps.lostfound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.steps.lostfound.R;
import com.steps.lostfound.model.Item;

/*
 * Created by Giorgi on 7/10/2015.
 */
public class MatchedItemsAdapter extends ParseQueryAdapter<Item> {

    private LayoutInflater inflater;

    public MatchedItemsAdapter(Context context, ParseQueryAdapter.QueryFactory<Item> queryFactory) {
        super(context, queryFactory);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getItemView(Item item, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.element_matched_item, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) view
                    .findViewById(R.id.matched_element_label);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView name = holder.name;
        name.setText(item.getDescription());

        return view;
    }

    private static class ViewHolder {
        TextView name;
    }
}
