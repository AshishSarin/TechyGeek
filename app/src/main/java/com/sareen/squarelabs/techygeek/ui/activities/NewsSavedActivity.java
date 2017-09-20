package com.sareen.squarelabs.techygeek.ui.activities;

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
import android.widget.ProgressBar;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.adapters.SavedPostsAdapter;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract.SavedPostsEntry;

public class NewsSavedActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<Cursor>
{

    private ProgressBar mSavedProgressBar;
    private RecyclerView mSavedListView;
    private RecyclerView.LayoutManager mSaveLayoutManager;
    private SavedPostsAdapter mSavedPostsAdapter;
    private static final int SAVED_POSTS_LOADER = -910;

    // Columns to retrieve from database for save post list
    private String[] saveListProjection =
            {
                    SavedPostsEntry._ID,
                    SavedPostsEntry.COLUMN_POST_ID,
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

        mSavedProgressBar = (ProgressBar)findViewById(R.id.saved_load_progress);

        // Setting up recycler view for saved post list
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
        mSavedProgressBar.setVisibility(View.VISIBLE);
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
        mSavedProgressBar.setVisibility(View.GONE);
        mSavedPostsAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        mSavedPostsAdapter.swapCursor(null);
    }
}
