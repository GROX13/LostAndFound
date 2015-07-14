package com.steps.lostfound.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.parse.ParseUser;
import com.steps.lostfound.R;
import com.steps.lostfound.fragments.FoundItemsFragment;
import com.steps.lostfound.fragments.HomeFragment;
import com.steps.lostfound.fragments.LostItemsFragment;
import com.steps.lostfound.fragments.MatchedItemsFragment;
import com.steps.lostfound.fragments.ResolvedItemsFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ParseUser user = ParseUser.getCurrentUser();
        TextView userEmail = (TextView) findViewById(R.id.user_email);
        TextView userName = (TextView) findViewById(R.id.user_name);
        userEmail.setText(user.getEmail());
        userName.setText((String) user.get("name"));

        FloatingActionButton newLostItem = (FloatingActionButton) findViewById(R.id.fab_item_lost);
        FloatingActionButton newFoundItem = (FloatingActionButton) findViewById(R.id.fab_item_found);

        newLostItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewLostItemActivity.class);
                startActivity(i);
            }
        });
        newFoundItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewFoundItemsActivity.class);
                startActivity(i);
            }
        });

        final ActionBarDrawerToggle actionBarDrawerToggle =
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
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft;
                if(R.id.action_settings != menuItem.getItemId())
                    menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        toolbar.setTitle(menuItem.getTitle());
                        ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragment_container, HomeFragment.newInstance());
                        ft.commit();
                        break;
                    case R.id.action_matched_items:
                        toolbar.setTitle(menuItem.getTitle());
                        ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragment_container, MatchedItemsFragment.newInstance());
                        ft.commit();
                        break;
                    case R.id.action_lost_items:
                        toolbar.setTitle(menuItem.getTitle());
                        ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragment_container, LostItemsFragment.newInstance());
                        ft.commit();
                        break;
                    case R.id.action_found_items:
                        toolbar.setTitle(menuItem.getTitle());
                        ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragment_container, FoundItemsFragment.newInstance());
                        ft.commit();
                        break;
                    case R.id.history:
                        toolbar.setTitle(menuItem.getTitle());
                        ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragment_container, ResolvedItemsFragment.newInstance());
                        ft.commit();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, "Not Yet implemented", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

}
