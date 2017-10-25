package com.gym.shancai.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 * 活动中心ViewPager的adapter
 */

public class ActivityCenterPagerAdapter extends FragmentPagerAdapter {

    FragmentManager manager;
    List<Fragment> fragments;
    List<String> list_title;

    public ActivityCenterPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.manager = fm;
        this.fragments = list;
    }

    public ActivityCenterPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> list_title) {
        super(fm);
        this.manager = fm;
        this.fragments = list;
        this.list_title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position % list_title.size());
    }
}
