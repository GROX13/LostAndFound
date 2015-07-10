package com.steps.lostfound.model;

import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.steps.lostfound.R;
import com.steps.lostfound.adapter.FoundItemsAdapter;
import com.steps.lostfound.adapter.LostItemsAdapter;
import com.steps.lostfound.adapter.MatchedItemsAdapter;
import com.steps.lostfound.adapter.ResolvedItemsAdapter;
import com.steps.lostfound.application.App;

import java.util.HashMap;

/**
 * Creates adapters and registers as listeners.
 * <p/>
 * Created by Giorgi on 7/10/2015.
 */
public class AdapterFactory {

    private static HashMap<Integer, ListAdapter> adapters = new HashMap<>();

    private static ListAdapter init(Activity activity, int id) {
        BaseAdapter adapter = null;
        switch (id) {
            case R.id.action_matched_items:
                adapter = new MatchedItemsAdapter(activity);
                App.getDataObservable().registerAdapter(adapter);
                adapters.put(id, adapter);
            case R.id.action_lost_items:
                adapter = new LostItemsAdapter(activity);
                App.getDataObservable().registerAdapter(adapter);
                adapters.put(id, adapter);
            case R.id.action_found_items:
                adapter = new FoundItemsAdapter(activity);
                App.getDataObservable().registerAdapter(adapter);
                adapters.put(id, adapter);
            case R.id.history:
                adapter = new ResolvedItemsAdapter(activity);
                App.getDataObservable().registerAdapter(adapter);
                adapters.put(id, adapter);
        }
        return adapter;
    }

    public static ListAdapter getAdapterFor(Activity activity, int id) {
        ListAdapter listAdapter = adapters.get(id);
        if (listAdapter == null) {
            listAdapter = init(activity, id);
        }
        return listAdapter;
    }

}
