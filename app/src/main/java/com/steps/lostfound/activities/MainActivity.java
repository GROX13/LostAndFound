package com.steps.lostfound.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.parse.Parse;
import com.parse.ParseUser;
import com.steps.lostfound.R;
import com.steps.lostfound.model.AdapterFactory;

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
//        contentFrame = (LinearLayout) findViewById(R.id.display_content);

        ParseUser user = ParseUser.getCurrentUser();
        TextView userEmail = (TextView)findViewById(R.id.user_email);
        TextView userName = (TextView)findViewById(R.id.user_name);
        userEmail.setText(user.getEmail());
        userName.setText((String)user.get("name"));

        FloatingActionButton newLostItem = (FloatingActionButton)findViewById(R.id.fab_item_lost);
        FloatingActionButton newFoundItem = (FloatingActionButton)findViewById(R.id.fab_item_found);

        newLostItem.setOnClickListener(new View.OnClickListener() {
            @Override
//<<<<<<< HEAD
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.action_home:
//                        contentFrame.removeAllViews();
//                        // TODO: Implement!
//                        Intent i = new Intent(MainActivity.this, MapActivity.class);
//                        startActivityForResult(i, 1);
//                        return true;
//                    case R.id.action_matched_items:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_matched_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.action_lost_items:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_lost_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.action_found_items:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_found_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.history:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_resolved_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.action_settings:
//                        // TODO: Implement!
//                        return true;
//                }
//                return false;
//=======
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NewLostItemActivity.class);
                startActivity(i);
//>>>>>>> origin/master
            }
        });
        newFoundItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NewFoundItemsActivity.class);
                startActivity(i);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.action_home:
//                        contentFrame.removeAllViews();
//                        // TODO: Implement!
//                        Intent i = new Intent(MainActivity.this, MapActivity.class);
//                        startActivityForResult(i, 1);
//                        return true;
//                    case R.id.action_matched_items:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_matched_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.action_lost_items:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_lost_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.action_found_items:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_found_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.history:
//                        setListViewUp((ListView)
//                                getLayoutInflater().inflate(R.layout.list_resolved_items, null),
//                                contentFrame, menuItem.getItemId());
//                        return true;
//                    case R.id.action_settings:
//                        // TODO: Implement!
//                        return true;
//                }
//                return false;
//            }
//        });

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                        R.string.openDrawer, R.string.closeDrawer) {
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

    private void setListViewUp(ListView listView, LinearLayout contentFrame, int itemId) {
        contentFrame.removeAllViews();
        listView.setAdapter(AdapterFactory
                .getAdapterFor(MainActivity.this, itemId));
        this.contentFrame.addView(listView);
    }

}
