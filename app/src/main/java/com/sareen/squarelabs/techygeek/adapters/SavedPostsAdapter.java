package com.sareen.squarelabs.techygeek.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.clickListeners.SaveNewsItemClickListener;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract.SavedPostsEntry;
import com.sareen.squarelabs.techygeek.model.Post;
import com.sareen.squarelabs.techygeek.ui.NewsDetailActivity;
import com.sareen.squarelabs.techygeek.utilities.Utility;
import com.squareup.picasso.Picasso;

/**
 * Created by ashish on 18/7/17.
 */

public class SavedPostsAdapter extends CursorRecyclerAdapter<SavedPostsAdapter.ItemViewHolder>
    implements SaveNewsItemClickListener
{
    Context mContext;
    SaveNewsItemClickListener itemClickListener;

    public SavedPostsAdapter(Context context, Cursor c)
    {
        super(c);
        this.mContext = context;
        itemClickListener = this;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, Cursor cursor)
    {

            String title = cursor.getString(cursor.getColumnIndex
                    (SavedPostsEntry.COLUMN_POST_TITLE));

            String mainImageUrl = cursor.getString(cursor.getColumnIndex
                    (SavedPostsEntry.COLUMN_POST_IMAGE));
            holder.mTitleView.setText(title);
            Picasso.with(mContext)
                    .load(mainImageUrl)
                    .into(holder.mTitleImageView);

            String text = cursor.getString(cursor.getColumnIndex
                    (SavedPostsEntry.COLUMN_POST_TEXT));
            String post_id = cursor.getString(cursor.getColumnIndex
                    (SavedPostsEntry.COLUMN_POST_ID));
            final Post postObj = new Post(post_id, mainImageUrl, title, text);

            holder.mItemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    itemClickListener.onSaveItemClick(postObj);
                }
            });

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_news, parent, false);
        ItemViewHolder viewHolder  = new ItemViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onSaveItemClick(Post post)
    {
        Intent detailIntent = new Intent(mContext, NewsDetailActivity.class);
        Bundle detailBundle = new Bundle();
        detailBundle.putString(Utility.DETAIL_POSTID, post.getPostId());
        detailBundle.putString(Utility.DETAIL_IMAGE, post.getMainImageUrl());
        detailBundle.putString(Utility.DETAIL_TTITLE, post.getTitle());
        detailBundle.putString(Utility.DETAIL_TEXT, post.getText());
        detailIntent.putExtras(detailBundle);
        mContext.startActivity(detailIntent);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private View mItemView;
        private TextView mTitleView;
        private ImageView mTitleImageView;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            this.mItemView = itemView;
            this.mTitleImageView = (ImageView)itemView.findViewById(R.id.title_image);
            this.mTitleView = (TextView)itemView.findViewById(R.id.title_text);
        }
    }
}
