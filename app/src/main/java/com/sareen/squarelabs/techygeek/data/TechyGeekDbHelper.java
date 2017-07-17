package com.sareen.squarelabs.techygeek.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sareen.squarelabs.techygeek.data.TechyGeekContract.SavedPostsEntry;

/**
 * Created by ashish on 12/7/17.
 */

public class TechyGeekDbHelper extends SQLiteOpenHelper
{
    // name of the file in which database is stored
    public static final String DATABASE_NAME = "techrumors.db";

    // Version of the database
    // if schema of database is changed
    // then this needs to be increased
    public static final int DATABASE_VERSION = 1;

    public TechyGeekDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String SQL_CREATE_SAVED_POSTS_TABLE = "CREATE TABLE "
                + SavedPostsEntry.TABLE_NAME + " ("
                + SavedPostsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SavedPostsEntry.COLUMN_POST_ID + " INTEGER NOT NULL, "
                + SavedPostsEntry.COLUMN_POST_TITLE + " TEXT NOT NULL, "
                + SavedPostsEntry.COLUMN_POST_TEXT + " TEXT NOT NULL, "
                + SavedPostsEntry.COLUMN_POST_IMAGE + " TEXT NOT NULL"
                + ")";
        db.execSQL(SQL_CREATE_SAVED_POSTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + SavedPostsEntry.TABLE_NAME);
        onCreate(db);
    }

}
