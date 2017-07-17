package com.sareen.squarelabs.techygeek.ui;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.adapters.SavedPostsAdapter;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract.SavedPostsEntry;

public class NewsSavedActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<Cursor>
{

    private RecyclerView mSavedListView;
    private RecyclerView.LayoutManager mSaveLayoutManager;
    private SavedPostsAdapter mSavedPostsAdapter;
    private static final int SAVED_POSTS_LOADER = -910;

    // Columns to retrieve from database for save post list
    private String[] saveListProjection =
            {
                    SavedPostsEntry._ID,
                    SavedPostsEntry.COLUMN_POST_TITLE,
                    SavedPostsEntry.COLUMN_POST_IMAGE,
                    SavedPostsEntry.COLUMN_POST_TEXT
            };

    private String sortOrder =
            SavedPostsEntry._ID + " DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_saved);

        // Setting up recylerview for saved post list
        mSavedListView = (RecyclerView)findViewById(R.id.list_saved_news);
        mSaveLayoutManager = new LinearLayoutManager(this);
        mSavedListView.setLayoutManager(mSaveLayoutManager);

        // Setting up the adapter for news list
        mSavedPostsAdapter = new SavedPostsAdapter(this, null);
        mSavedListView.setAdapter(mSavedPostsAdapter);

        // Initialize the loader
        getSupportLoaderManager()
                .initLoader(SAVED_POSTS_LOADER, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        return new CursorLoader
                (
                        this,
                        SavedPostsEntry.CONTENT_URI,
                        saveListProjection,
                        null,
                        null,
                        sortOrder
                );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        mSavedPostsAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        mSavedPostsAdapter.swapCursor(null);
    }
}
