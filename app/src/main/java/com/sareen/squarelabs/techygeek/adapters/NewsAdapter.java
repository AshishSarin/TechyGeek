package com.sareen.squarelabs.techygeek.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.model.Post;
import com.squareup.picasso.Picasso;

/**
 * Created by Ashish on 28-05-2017.
 */

public class NewsAdapter extends FirebaseRecyclerAdapter<Post, NewsAdapter.NewsViewHolder>
{
    private Context mContext;

    public NewsAdapter(Class<Post> modelClass, int modelLayout, Class<NewsViewHolder> viewHolderClass, Query ref, Context context)
    {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(NewsViewHolder viewHolder, Post model, int position)
    {
        // getting data from model
        String title = model.getTitle();
        String mainImageUrl = model.getMainImageUrl();

        // Populating views
        viewHolder.mTitleTextView.setText(title);
        // Loading image from url
        Picasso.with(mContext)
                .load(mainImageUrl)
                .into(viewHolder.mTitleImageView);

    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder
    {

        View itemView;
        TextView mTitleTextView;
        ImageView mTitleImageView;

        public NewsViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            this.mTitleImageView = (ImageView)itemView.findViewById(R.id.title_image);
            this.mTitleTextView = (TextView) itemView.findViewById(R.id.title_text);
        }
    }
}
