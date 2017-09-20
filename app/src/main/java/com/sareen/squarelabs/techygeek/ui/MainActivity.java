package com.sareen.squarelabs.techygeek.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sareen.squarelabs.techygeek.R;

public class MainActivity extends AppCompatActivity
{

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:

                    Toast.makeText(MainActivity.this, "Home Tab", Toast.LENGTH_SHORT)
                            .show();
                    return true;
                case R.id.navigation_dashboard:
                    // load the dashboard fragment
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_content, dashboardFragment)
                            .commit();
                    Toast.makeText(MainActivity.this, "Dashboard Tab", Toast.LENGTH_SHORT)
                            .show();
                    return true;
                case R.id.navigation_downloads:
                    Toast.makeText(MainActivity.this, "Downloads Tab", Toast.LENGTH_SHORT)
                            .show();
                    return true;
                case R.id.navigation_home1:
                case R.id.navigation_dashboard11:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.home_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // hide bottom navigation view on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehaviour());

        findViewById(R.id.dashboard_tabLayout).setVisibility(View.GONE);

        // add home fragment initiallly

        Toast.makeText(MainActivity.this, "Home Tab", Toast.LENGTH_SHORT)
                .show();
    }
}