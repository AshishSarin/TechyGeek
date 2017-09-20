package com.sareen.squarelabs.techygeek.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sareen.squarelabs.techygeek.ui.fragments.NewsListFragment;

/**
 * Created by ashish on 14/9/17.
 */

public class DashboardPagerAdapter extends FragmentStatePagerAdapter
{

    public DashboardPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        // for now return same fragment
        //T ODO: return fragment based on position
        Fragment techListFragment = NewsListFragment.getInstance();
        return techListFragment;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return "Tech News";
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}
