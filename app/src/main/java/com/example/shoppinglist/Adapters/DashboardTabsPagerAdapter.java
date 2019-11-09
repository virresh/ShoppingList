package com.example.shoppinglist.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.shoppinglist.Fragments.RecyclerFragment;

public class DashboardTabsPagerAdapter extends FragmentStatePagerAdapter {

    private RecyclerFragment tab1;
    private RecyclerFragment tab2;

    public DashboardTabsPagerAdapter(FragmentManager fm) {
        super(fm);
        tab1 = new RecyclerFragment();
        Bundle args1 = new Bundle();
        args1.putInt("TAB_NUM", 1);
        tab1.setArguments(args1);

        Bundle args2 = new Bundle();
        args2.putInt("TAB_NUM", 2);
        tab2 = new RecyclerFragment();
        tab2.setArguments(args2);
    }

    @Override
    public Fragment getItem(int position) {
//        Fragment fragment = new ShoppingItemFragment();
//        Bundle args = new Bundle();
//
//        args.putInt(ShoppingItemFragment.ARG_OBJECT, position + 1);
//        fragment.setArguments(args);
//        return fragment;
        switch (position) {
            case 0: return tab1;
            case 1: return tab2;
        }
        return null;
//        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(@NonNull Object object){
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "Title";
        switch (position) {
            case 0: title="Current List"; break;
            case 1: title="Completed List"; break;
        }
        return title;
    }
}
