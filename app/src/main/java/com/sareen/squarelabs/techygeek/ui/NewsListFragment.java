package com.sareen.squarelabs.techygeek.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sareen.squarelabs.techygeek.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment
{


    public NewsListFragment()
    {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_new_list, container, false);
    }

}
