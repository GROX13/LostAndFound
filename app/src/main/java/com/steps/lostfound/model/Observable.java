package com.steps.lostfound.model;

import android.widget.BaseAdapter;

/**
 * TODO: More methods should be added!
 */
public interface Observable {

    void registerAdapter(BaseAdapter adapter);

    void notifyObservers();

}
