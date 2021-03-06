package com.sareen.squarelabs.techygeek.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.adapters.NewsAdapter;
import com.sareen.squarelabs.techygeek.model.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment
{

    private RecyclerView mNewsListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsAdapter mNewsAdapter;
    private DatabaseReference mDatabaseRef;
    private ProgressBar mProgressBar;


    public NewsListFragment()
    {
        // Required empty public constructor
        mDatabaseRef = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("posts")
                .child("mobilePosts");
    }

    public static NewsListFragment getInstance()
    {
        NewsListFragment newsListFragment = new NewsListFragment();
        return newsListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_new_list, container, false);

        // Setting up the pogress bar
        mProgressBar = (ProgressBar)rootView.findViewById(R.id.news_load_progress);
        mProgressBar.setVisibility(View.VISIBLE);

        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

        // Setting up recycler view for news list
        mNewsListView = (RecyclerView)rootView.findViewById(R.id.list_news);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mNewsListView.setLayoutManager(mLayoutManager);

        // Setting up the adapter for news list
        mNewsAdapter = new NewsAdapter(Post.class, R.layout.list_item_news,
                NewsAdapter.NewsViewHolder.class,
                mDatabaseRef, getActivity());
        mNewsListView.setAdapter(mNewsAdapter);


        return rootView;
    }

}
