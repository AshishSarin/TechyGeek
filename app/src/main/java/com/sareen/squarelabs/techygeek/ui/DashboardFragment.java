package com.sareen.squarelabs.techygeek.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sareen.squarelabs.techygeek.R;
import com.sareen.squarelabs.techygeek.adapters.DashboardPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment
{

    ViewPager mDashboardPager;
    DashboardPagerAdapter mDashboardPagerAdapter;

    public DashboardFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mDashboardPager = (ViewPager)rootView.findViewById(R.id.dashboard_pager);
        mDashboardPagerAdapter = new DashboardPagerAdapter(getChildFragmentManager());
        mDashboardPager.setAdapter(mDashboardPagerAdapter);
        return rootView;
    }

}
