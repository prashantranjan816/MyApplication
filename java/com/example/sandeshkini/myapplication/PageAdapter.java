package com.example.sandeshkini.myapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments = new ArrayList<Fragment>();
    List<String> title = new ArrayList<String>();

    public PageAdapter(FragmentManager fm) {
        super( fm );
    }

    public void addFragment(Fragment frag, String tit) {
        this.fragments.add( (Fragment) frag );
        this.title.add( tit );

    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get( i );
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get( position );
    }
}
