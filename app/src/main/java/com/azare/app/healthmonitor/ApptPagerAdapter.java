package com.azare.app.healthmonitor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.ArrayList;
import java.util.List;

public class ApptPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public static ApptPagerAdapter instance = null;

    private ApptPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static ApptPagerAdapter initialize(FragmentManager fm) {
        Log.e(HMUtils.LOGTAG, "ApptPagerAdapter initialize Start");
        instance = new ApptPagerAdapter(fm);

        return instance;
    }

    public static ApptPagerAdapter getInstance() {
        return instance;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title)
    {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }


}
