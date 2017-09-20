package com.sareen.squarelabs.techygeek.ui.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.adapters.SavedPostsAdapter;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadsFragment extends Fragment
    implements LoaderManager.LoaderCallbacks<Cursor>
{


    public DownloadsFragment()
    {
        // Required empty public constructor
    }

    public static DownloadsFragment getInstance()
    {
        DownloadsFragment fragment = new DownloadsFragment();
        // set argument to fragment
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_downloads, container, false);
        mSavedProgressBar = (ProgressBar)rootView.findViewById(R.id.saved_load_progress);

        // Setting up recycler view for saved post list
        mSavedListView = (RecyclerView)rootView.findViewById(R.id.list_saved_news);
        mSaveLayoutManager = new LinearLayoutManager(getActivity());
        mSavedListView.setLayoutManager(mSaveLayoutManager);

        // Setting up the adapter for news list
        mSavedPostsAdapter = new SavedPostsAdapter(getActivity(), null);
        mSavedListView.setAdapter(mSavedPostsAdapter);

        // Initialize the loader
        getActivity().getSupportLoaderManager()
                .initLoader(SAVED_POSTS_LOADER, null, this);
        return rootView;
    }
    private ProgressBar mSavedProgressBar;
    private RecyclerView mSavedListView;
    private RecyclerView.LayoutManager mSaveLayoutManager;
    private SavedPostsAdapter mSavedPostsAdapter;
    private static final int SAVED_POSTS_LOADER = -910;

    // Columns to retrieve from database for save post list
    private String[] saveListProjection =
            {
                    TechyGeekContract.SavedPostsEntry._ID,
                    TechyGeekContract.SavedPostsEntry.COLUMN_POST_ID,
                    TechyGeekContract.SavedPostsEntry.COLUMN_POST_TITLE,
                    TechyGeekContract.SavedPostsEntry.COLUMN_POST_IMAGE,
                    TechyGeekContract.SavedPostsEntry.COLUMN_POST_TEXT
            };

    private String sortOrder =
            TechyGeekContract.SavedPostsEntry._ID + " DESC";



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        mSavedProgressBar.setVisibility(View.VISIBLE);
        return new CursorLoader
                (
                        getActivity(),
                        TechyGeekContract.SavedPostsEntry.CONTENT_URI,
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
