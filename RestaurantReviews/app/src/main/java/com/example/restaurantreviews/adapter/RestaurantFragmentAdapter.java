package com.example.restaurantreviews.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class RestaurantFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public RestaurantFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public RestaurantFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> list){
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
