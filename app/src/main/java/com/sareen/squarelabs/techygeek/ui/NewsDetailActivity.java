package com.sareen.squarelabs.techygeek.ui;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract;
import com.sareen.squarelabs.techygeek.utilities.Utility;
import com.squareup.picasso.Picasso;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract.SavedPostsEntry;

public class NewsDetailActivity extends AppCompatActivity
{

    private int calling_code;
    private TextView detailTitle;
    private TextView detailText;
    private ImageView detailImage;

    private String post_title;
    private String post_text;
    private String post_image_url;
    private String post_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Bundle detailBundle = getIntent().getExtras();

        post_title = detailBundle.getString(Utility.DETAIL_TTITLE);
        post_text = detailBundle.getString(Utility.DETAIL_TEXT);
        post_image_url = detailBundle.getString(Utility.DETAIL_IMAGE);
        post_id = detailBundle.getString(Utility.DETAIL_POSTID);

        detailImage = (ImageView)findViewById(R.id.detail_image);
        detailText = (TextView)findViewById(R.id.detail_text);
        detailTitle = (TextView)findViewById(R.id.detail_title);

        detailText.setText(post_text);
        detailTitle.setText(post_title);

        Picasso.with(this)
                .load(post_image_url)
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
                savePost();
                return true;
            case R.id.action_settings:
                // open settings
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void savePost()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SavedPostsEntry.COLUMN_POST_ID, post_id);
        contentValues.put(SavedPostsEntry.COLUMN_POST_TITLE, post_title);
        contentValues.put(SavedPostsEntry.COLUMN_POST_TEXT, post_text);
        contentValues.put(SavedPostsEntry.COLUMN_POST_TEXT, post_text);
        contentValues.put(SavedPostsEntry.COLUMN_POST_IMAGE, post_image_url);
        getContentResolver().insert(SavedPostsEntry.CONTENT_URI, contentValues);
        Toast.makeText(this, "Article saved in Downloads", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }
}
