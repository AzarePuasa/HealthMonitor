package com.azare.app.healthmonitor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ApptPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public static ApptPagerAdapter instance;

    private ApptPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static ApptPagerAdapter initialize(FragmentManager fm) {
        instance = new ApptPagerAdapter(fm);

        return instance;
    }

    public static ApptPagerAdapter getInstance() {
        return instance;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title, int position) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(position, title);
    }

    public void removeAllFragment() {
        mFragmentList.clear();
        mFragmentTitleList.clear();
    }
}
