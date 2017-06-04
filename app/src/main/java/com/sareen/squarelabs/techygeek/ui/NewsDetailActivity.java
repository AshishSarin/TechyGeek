package com.sareen.squarelabs.techygeek.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sareen.squarelabs.techygeek.R;

public class NewsDetailActivity extends TechyGeekActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FrameLayout mFrameLayout = (FrameLayout)findViewById(R.id.layout_base);
        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View detailActivityView = mLayoutInflater.inflate(R.layout.activity_news_detail, null, false);
        mFrameLayout.addView(detailActivityView);

        //Setting up the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detail Activity");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(null);

        String detailText = getIntent().getStringExtra("detailText");
        TextView detailTextView = (TextView)detailActivityView.findViewById(R.id.detail_text);
        detailTextView.setText(detailText);
    }
}
