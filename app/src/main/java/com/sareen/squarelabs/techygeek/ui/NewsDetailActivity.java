package com.sareen.squarelabs.techygeek.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.utilities.Utility;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity
{

    private int calling_code;
    private TextView detailTitle;
    private TextView detailText;
    private ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Bundle detailBundle = getIntent().getExtras();

        String title = detailBundle.getString(Utility.DETAIL_TTITLE);
        String text = detailBundle.getString(Utility.DETAIL_TEXT);
        String image = detailBundle.getString(Utility.DETAIL_IMAGE);
        String id = detailBundle.getString(Utility.DETAIL_POSTID);

        detailImage = (ImageView)findViewById(R.id.detail_image);
        detailText = (TextView)findViewById(R.id.detail_text);
        detailTitle = (TextView)findViewById(R.id.detail_title);

        detailText.setText(text);
        detailTitle.setText(title);

        Picasso.with(this)
                .load(image)
                .into(detailImage);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId = item.getItemId();

        switch (itemId)
        {
            case R.id.action_save:
                // save article
                return true;
            case R.id.action_settings:
                // open settings
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }
}
