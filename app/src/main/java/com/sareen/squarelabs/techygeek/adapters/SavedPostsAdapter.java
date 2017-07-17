package com.sareen.squarelabs.techygeek.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract.SavedPostsEntry;
import com.squareup.picasso.Picasso;

/**
 * Created by ashish on 18/7/17.
 */

public class SavedPostsAdapter extends CursorRecyclerAdapter<SavedPostsAdapter.ItemViewHolder>
{
    Context mContext;

    public SavedPostsAdapter(Context context, Cursor c)
    {
        super(c);
        this.mContext = context;
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
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_news, parent, false);
        ItemViewHolder viewHolder  = new ItemViewHolder(view);
        return viewHolder;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTitleView;
        private ImageView mTitleImageView;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            mTitleImageView = (ImageView)itemView.findViewById(R.id.title_image);
            mTitleView = (TextView)itemView.findViewById(R.id.title_text);
        }
    }
}
