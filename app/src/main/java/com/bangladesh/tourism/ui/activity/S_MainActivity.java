package com.bangladesh.tourism.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;


import com.bangladesh.tourism.R;
import com.bangladesh.tourism.ui.fragment.FiveFragment;
import com.bangladesh.tourism.ui.fragment.FourFragment;
import com.bangladesh.tourism.ui.fragment.OneFragment;
import com.bangladesh.tourism.ui.fragment.ThreeFragment;
import com.bangladesh.tourism.ui.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class S_MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        if (savedInstanceState != null) {
            viewPager.setCurrentItem(savedInstanceState.getInt("item"));
        }
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {

        ImageView tabOne = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
     //   tabOne.setImageResource(R.drawable.web);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        ImageView tabTwo = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
       // tabTwo.setImageResource(R.drawable.cht);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        ImageView tabThree = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
      //  tabThree.setImageResource(R.drawable.cht);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        ImageView tabFour = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
      //  tabFour.setImageResource(R.drawable.list);
        tabLayout.getTabAt(3).setCustomView(tabFour);

        ImageView tabFive = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
       // tabFive.setImageResource(R.drawable.ques);
        tabLayout.getTabAt(4).setCustomView(tabFive);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new OneFragment(),"One");
        adapter.addFrag(new TwoFragment(),"Two");
        adapter.addFrag(new ThreeFragment(),"Three");
        adapter.addFrag(new FourFragment(),"Four");
        adapter.addFrag(new FiveFragment(),"Five");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("item", viewPager.getCurrentItem());
    }
}
