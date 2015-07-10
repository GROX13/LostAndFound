package com.steps.lostfound.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.steps.lostfound.R;
import com.steps.lostfound.adapter.MatchedItemsAdapter;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    private LinearLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        contentFrame = (LinearLayout) findViewById(R.id.display_content);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        contentFrame.removeAllViews();
                        // TODO: Implement!
                        return true;
                    case R.id.action_matched_items:
                        contentFrame.removeAllViews();
                        ListView listView = (ListView) getLayoutInflater()
                                .inflate(R.layout.list_matched_items, null);
                        listView.setAdapter(new MatchedItemsAdapter(MainActivity.this));
                        contentFrame.addView(listView);
                        return true;
                    case R.id.action_lost_items:
                        contentFrame.removeAllViews();
                        // TODO: Implement!
                        return true;
                    case R.id.action_found_items:
                        contentFrame.removeAllViews();
                        // TODO: Implement!
                        return true;
                    case R.id.history:
                        contentFrame.removeAllViews();
                        // TODO: Implement!
                        return true;
                    case R.id.action_settings:
                        // TODO: Implement!
                        return true;
                }
                return false;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


}
