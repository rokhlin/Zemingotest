package com.selfaps.zemingotest.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.selfaps.zemingotest.R;
import com.selfaps.zemingotest.utils.Constants;
import com.selfaps.zemingotest.view.CarsFragment;
import com.selfaps.zemingotest.view.NewsFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return CarsFragment.newInstance(R.layout.fragment_cars, new String[]{Constants.CATEGORY_CARS[0]});
            case 1:
                return CarsFragment.newInstance(R.layout.fragment_cars, new String[]{Constants.CATEGORY_CARS[0]});
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}