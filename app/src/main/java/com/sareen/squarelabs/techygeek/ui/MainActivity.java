package com.sareen.squarelabs.techygeek.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
                    NewsListFragment newsListFragment = NewsListFragment.getInstance();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.dashboard_content, newsListFragment)
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
        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.home_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}