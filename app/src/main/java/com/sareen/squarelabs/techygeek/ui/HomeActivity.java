package com.sareen.squarelabs.techygeek.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sareen.squarelabs.techygeek.R;

public class HomeActivity extends TechyGeekActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // adding home activity view to base activity
        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.layout_base);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View homeActivityView = inflater.inflate(R.layout.activity_home, null, false);
        mFrameLayout.addView(homeActivityView);

        // Settingup the action bar
        getSupportActionBar().setTitle("Techy Geek");

        // show news list fragment
        NewsListFragment newsListFragment = NewsListFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_activity_view, newsListFragment)
                .commit();

    }
}
