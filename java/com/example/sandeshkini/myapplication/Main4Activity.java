package com.example.sandeshkini.myapplication;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {
    TabLayout tabLayout;
    PageAdapter pagerAdapter;
    AppBarLayout appBarLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tabLayout = (TabLayout) findViewById( R.id.tab );
        //appBarLayout=(AppBarLayout)findViewById(R.id.appbarlayout);
        viewPager = (ViewPager) findViewById( R.id.viewpager );
        pagerAdapter = new PageAdapter( getSupportFragmentManager() );
        pagerAdapter.addFragment( new BlankFragment(), "F1" );
        pagerAdapter.addFragment( new Fragment2(), "F2" );
        viewPager.setAdapter( pagerAdapter );
        tabLayout.setupWithViewPager( viewPager );
    }
}
