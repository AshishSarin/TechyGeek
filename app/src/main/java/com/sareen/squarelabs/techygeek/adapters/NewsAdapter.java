package com.sareen.squarelabs.techygeek.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.clickListeners.NewsListItemClickListener;
import com.sareen.squarelabs.techygeek.model.Post;
import com.sareen.squarelabs.techygeek.ui.activities.NewsDetailActivity;
import com.sareen.squarelabs.techygeek.utilities.Utility;
import com.squareup.picasso.Picasso;

/**
 * Created by Ashish on 28-05-2017.
 */

public class NewsAdapter extends FirebaseRecyclerAdapter<Post, NewsAdapter.NewsViewHolder> implements
        NewsListItemClickListener
{
    private Context mContext;
    private int lastPosition = -1;
    private NewsListItemClickListener itemClickListener;

    public NewsAdapter(Class<Post> modelClass, int modelLayout, Class<NewsViewHolder> viewHolderClass, Query ref, Context context)
    {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
        itemClickListener = this;
    }

    @Override
    protected void populateViewHolder(NewsViewHolder viewHolder, Post model, final int position)
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


        // adding animation to the view
        setAnimation(viewHolder.itemView, position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                itemClickListener.onItemClick(v, position);
            }
        });



    }

    private void setAnimation(View itemView, int position)
    {
        // if the bound view wasn't previously displayed on screen, it's animated.
        if(position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.list_item_right_to_left);
            itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(NewsViewHolder holder)
    {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();

    }

    @Override
    public void onItemClick(View view, int position)
    {
        Post post = getItem(position);
        Intent detailIntent = new Intent(mContext, NewsDetailActivity.class);
        Bundle detailBundle = new Bundle();
        detailBundle.putString(Utility.DETAIL_TTITLE, post.getTitle());
        detailBundle.putString(Utility.DETAIL_TEXT, post.getText());
        detailBundle.putString(Utility.DETAIL_POSTID, post.getPostId());
        detailBundle.putString(Utility.DETAIL_IMAGE, post.getMainImageUrl());
        detailBundle.putInt(Utility.DETAIL_CALLING_ACTIVITY, Utility.HOME_ACTIVITY_CALLING);
        detailIntent.putExtras(detailBundle);
        mContext.startActivity(detailIntent);
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

        public void clearAnimation()
        {
            itemView.clearAnimation();
        }
    }
}
