package com.bangladesh.tourism.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.rabbitshat.www.testproject.Fragment.LocationFragmentOne;
import com.rabbitshat.www.testproject.Fragment.LocationFragmentTwo;
import com.rabbitshat.www.testproject.R;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        viewPager = (ViewPager) findViewById(R.id.place_viewpager);
        setupViewPager(viewPager);
        if (savedInstanceState != null) {
            viewPager.setCurrentItem(savedInstanceState.getInt("item"));
        }
        tabLayout = (TabLayout) findViewById(R.id.placetab);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.location_custom_tab, null);
      //  tabOne.setCompoundDrawables(R.drawable.web);
        tabOne.setText("info");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.location_custom_tab, null);
        tabTwo.setText("Review");
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new LocationFragmentOne(),"One");
        adapter.addFrag(new LocationFragmentTwo(),"Two");
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
