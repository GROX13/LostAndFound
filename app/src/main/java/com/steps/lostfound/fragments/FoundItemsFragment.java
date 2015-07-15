package com.steps.lostfound.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.steps.lostfound.R;
import com.steps.lostfound.adapter.MatchedItemsAdapter;
import com.steps.lostfound.model.Item;

public class FoundItemsFragment extends Fragment {

    private ParseQueryAdapter<Item> adapter;

    public static FoundItemsFragment newInstance() {
        FoundItemsFragment fragment = new FoundItemsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FoundItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_found_items, container, false);
        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<Item> factory = new ParseQueryAdapter.QueryFactory<Item>() {
            public ParseQuery<Item> create() {
                ParseQuery<Item> query = Item.getQuery();
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }
        };

        adapter = new MatchedItemsAdapter(getActivity(), factory);
        ListView itemsListView = (ListView) v.findViewById(R.id.items_list_view);
        itemsListView.setAdapter(adapter);

        return v;
    }


}
