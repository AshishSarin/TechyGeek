package com.sareen.squarelabs.techygeek.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.utilities.Utility;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity
{

    private int calling_code;
    private TextView detailTitle;
    private TextView detailText;
    private ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Bundle detailBundle = getIntent().getExtras();

        String title = detailBundle.getString(Utility.DETAIL_TTITLE);
        String text = detailBundle.getString(Utility.DETAIL_TEXT);
        String image = detailBundle.getString(Utility.DETAIL_IMAGE);
        String id = detailBundle.getString(Utility.DETAIL_POSTID);

        detailImage = (ImageView)findViewById(R.id.detail_image);
        detailText = (TextView)findViewById(R.id.detail_text);
        detailTitle = (TextView)findViewById(R.id.detail_title);

        detailText.setText(text);
        detailTitle.setText(title);

        Picasso.with(this)
                .load(image)
                .into(detailImage);




    }


}
