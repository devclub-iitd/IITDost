package com.example.iitdost;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.view.GravityCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private DrawerLayout mDrawerLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_requests:
                    mTextMessage.setText(R.string.title_requests);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        //ActionBar for navDrawer button
        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mTextMessage = (TextView) findViewById(R.id.message);

        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.navigation);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.navigation);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
        bottomNavigation.setAccentColor(Color.parseColor("#000000"));
        bottomNavigation.setInactiveColor(Color.parseColor("#c3baba"));
        bottomNavigation.setTitleTextSize(45,30);
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.opensans_semibold);
        bottomNavigation.setTitleTypeface(typeface);

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
