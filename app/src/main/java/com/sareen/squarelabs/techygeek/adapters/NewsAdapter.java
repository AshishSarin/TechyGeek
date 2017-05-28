package com.sareen.squarelabs.techygeek.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.sareen.squarelabs.techygeek.model.Post;

/**
 * Created by Ashish on 28-05-2017.
 */

public class NewsAdapter extends FirebaseRecyclerAdapter<Post, NewsAdapter.NewsViewHolder>
{
    public NewsAdapter(Class<Post> modelClass, int modelLayout, Class<NewsViewHolder> viewHolderClass, Query ref)
    {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(NewsViewHolder viewHolder, Post model, int position)
    {

    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder
    {

        public NewsViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
