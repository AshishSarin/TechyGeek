package com.sareen.squarelabs.techygeek.model;

/**
 * Created by Ashish on 28-05-2017.
 */

public class Post
{
    String postId;
    String mainImageUrl;
    String title;
    String text;

    public Post()
    {
    }

    public Post(String postId, String mainImageUrl, String title, String text)
    {
        this.postId = postId;
        this.mainImageUrl = mainImageUrl;
        this.title = title;
        this.text = text;
    }

    public String getPostId()
    {
        return postId;
    }

    public String getMainImageUrl()
    {
        return mainImageUrl;
    }

    public String getTitle()
    {
        return title;
    }

    public String getText()
    {
        return text;
    }
}
