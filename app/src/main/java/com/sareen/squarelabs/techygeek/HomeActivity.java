package com.sareen.squarelabs.techygeek;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;

public class HomeActivity extends TechyGeekActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // adding home activity view to base activity
        ConstraintLayout mConstraintLayout = (ConstraintLayout)findViewById(R.id.layout_base);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View homeActivityView = inflater.inflate(R.layout.activity_home, null, false);
        mConstraintLayout.addView(homeActivityView);

        // Settingup the action bar
        getSupportActionBar().setTitle("Techy Geek");


    }
}
