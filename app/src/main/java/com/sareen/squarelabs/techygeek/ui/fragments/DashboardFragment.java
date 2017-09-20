package com.sareen.squarelabs.techygeek.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
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
    TabLayout tabLayout;

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
        TabLayout tabLayout = (TabLayout)getActivity().findViewById(R.id.dashboard_tabLayout);
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(mDashboardPager);

        return rootView;
    }

}
