package com.example.shoppinglist.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shoppinglist.Adapters.DashboardTabsPagerAdapter;
import com.example.shoppinglist.R;
import com.google.android.material.tabs.TabLayout;

public class DashboardTabsFragment extends Fragment {
    DashboardTabsPagerAdapter dashboardPageAdapter;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.dashboard_tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        dashboardPageAdapter = new DashboardTabsPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(dashboardPageAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void dataUpdated(){
        dashboardPageAdapter.notifyDataSetChanged();
    }
}
