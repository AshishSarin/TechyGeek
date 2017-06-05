package com.sareen.squarelabs.techygeek.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sareen.squarelabs.techygeek.R;

public class NewsDetailActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        String detailText = getIntent().getStringExtra("detailText");
        TextView detailTextView = (TextView)findViewById(R.id.detail_text);
        detailTextView.setText(detailText);
    }




}
