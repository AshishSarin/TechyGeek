package com.sareen.squarelabs.techygeek.ui.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.utilities.Utility;
import com.squareup.picasso.Picasso;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract.SavedPostsEntry;

public class NewsDetailActivity extends AppCompatActivity
{
    private TextView detailTitle;
    private TextView detailText;
    private ImageView detailImage;

    private String post_title;
    private String post_text;
    private String post_image_url;
    private String post_id;

    private boolean isSaved;
    private int calling_code;

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
        calling_code = detailBundle.getInt(Utility.DETAIL_CALLING_ACTIVITY);

        detailImage = (ImageView)findViewById(R.id.detail_image);
        detailText = (TextView)findViewById(R.id.detail_text);
        detailTitle = (TextView)findViewById(R.id.detail_title);

        detailText.setText(post_text);
        detailTitle.setText(post_title);

        Picasso.with(this)
                .load(post_image_url)
                .into(detailImage);

        // initialize the save option in menu
        initSaveOption();


    }

    private void initSaveOption()
    {
        // Keeping this flag false, to show save option in menu
        isSaved = false;



        isSaved = (calling_code == Utility.SAVE_ACTIVITY_CALLING);
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
        if(!isSaved)
        {

            if(isAlreadyDownloaded())
            {
                Toast.makeText(this, "Article already downloaded", Toast.LENGTH_SHORT)
                        .show();
            }

            else
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
            // toggle save flag
            isSaved = true;

            invalidateOptionsMenu();
        }
        else
        {
            // TODO:
            // Delete the post from database
            // and if the calling activity is Saved activity,
            // then close the detail activity
            // show a snack bar in save activity with undo option of delete operation
            // Show toast of already downloaded
            if(deletePost())
            {
                // Post is deleted
                Toast.makeText(this, "Article removed from downloads", Toast.LENGTH_SHORT)
                        .show();
                if(calling_code == Utility.SAVE_ACTIVITY_CALLING)
                {
                    // if calling activity is save activity
                    // Stop detail activity
                    this.finish();
                }
                else
                {
                    isSaved = false;
                    invalidateOptionsMenu();
                }
            }
            else
            {
                // Post deletion was unsuccessful
                Toast.makeText(this, "Not able to remove article from downloads",
                        Toast.LENGTH_SHORT).show();
            }


        }
    }

    private boolean isAlreadyDownloaded()
    {
        Cursor c = getContentResolver().query
                (SavedPostsEntry.CONTENT_URI, null, SavedPostsEntry.COLUMN_POST_ID + "=?",
                        new String[]{post_id}, null);

        if(c.getCount() == 0)
        {
            return false;
        }
        return true;
    }

    private boolean deletePost()
    {
        int numDelete = getContentResolver().delete(SavedPostsEntry.CONTENT_URI,
                SavedPostsEntry.COLUMN_POST_ID + "=?", new String[]{post_id});
        if(numDelete != 0)
        {
            return true;
        }
        return false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem saveItem = menu.findItem(R.id.action_save);
        if(!isSaved)
        {
            saveItem.setIcon(R.drawable.action_download_icon);
        }
        else
        {
            saveItem.setIcon(R.drawable.action_delete_icon);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
