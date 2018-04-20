package com.example.iitdost.HomeScreen;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.view.GravityCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.example.iitdost.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Remove default title text
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // Get access to the custom title view
        // TextView mTitle = toolbar.findViewById(R.id.toolbar_title);

        //ActionBar for navDrawer button
        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Initialize bottom navigation bar with different colors and menu items
        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        AHBottomNavigation bottomNavigation = findViewById(R.id.navigation);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.navigation);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);

        //Setting Color for active icons on bottom navigation bar
        bottomNavigation.setAccentColor(Color.parseColor("#000000"));

        //Setting Color for inactive icons on bottom navigation bar
        bottomNavigation.setInactiveColor(Color.parseColor("#C3BABA"));

        //Setting text size for active and inactive states
        bottomNavigation.setTitleTextSizeInSp(16  , 13);

        //Setting typeface for titles on bottom navigation bar
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.opensans_semibold);
        bottomNavigation.setTitleTypeface(typeface);

        // Setting listeners for tab selection
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                //If previously selected then skip
                if(wasSelected){
                    return true;
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        Log.d(TAG, "onNavigationItemSelected: Navigating to Home");
                        // Replace the contents of the container with the new fragment
                        ft.replace(R.id.home_frag_space, new HomeFragment());
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft.commit();
//                    mTextMessage.setText(R.string.title_home);
                        return true;
                    case 1:
                        // Replace the contents of the container with the new fragment
                        ft.replace(R.id.home_frag_space, new NotificationFragment());
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft.commit();
//                    mTextMessage.setText(R.string.title_notifications);
                        return true;
                    case 2:
                        // Replace the contents of the container with the new fragment
                        ft.replace(R.id.home_frag_space, new RequestsFragment());
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft.commit();
//                    mTextMessage.setText(R.string.title_requests);
                        return true;
                }
                return false;
            }
        });

        //Initiating screen on home fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.home_frag_space, new HomeFragment());
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
