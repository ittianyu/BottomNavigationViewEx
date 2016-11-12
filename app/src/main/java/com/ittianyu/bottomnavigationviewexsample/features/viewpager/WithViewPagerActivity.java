package com.ittianyu.bottomnavigationviewexsample.features.viewpager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.common.base.BaseFragment;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityWithViewPagerBinding;

import java.util.ArrayList;
import java.util.List;

public class WithViewPagerActivity extends AppCompatActivity {
    private static final String TAG = WithViewPagerActivity.class.getSimpleName();
    private ActivityWithViewPagerBinding bind;
    private VpAdapter adapter;

    // collections
    private SparseIntArray items;// used for change ViewPager selected item
    private List<Fragment> fragments;// used for ViewPager adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_with_view_pager);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_with_view_pager);

        initView();
        initData();
        initEvent();
    }

    /**
     * change BottomNavigationViewEx style
     */
    private void initView() {
        bind.bnve.enableItemShiftingMode(true);
        bind.bnve.enableAnimation(false);
    }

    /**
     * create fragments
     */
    private void initData() {
        fragments = new ArrayList<>(3);
        items = new SparseIntArray(3);

        // create music fragment and add it
        BaseFragment musicFragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.music));
        musicFragment.setArguments(bundle);

        // create backup fragment and add it
        BaseFragment backupFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        backupFragment.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment friendsFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.friends));
        friendsFragment.setArguments(bundle);

        // add to fragments for adapter
        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(friendsFragment);

        // add to items for change ViewPager item
        items.put(R.id.i_music, 0);
        items.put(R.id.i_backup, 1);
        items.put(R.id.i_friends, 2);

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        bind.vp.setAdapter(adapter);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        bind.bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = 0;
//                switch (item.getItemId()) {
//                    case R.id.menu_music:
//                        id = 0;
//                        break;
//                    case R.id.menu_backup:
//                        id = 1;
//                        break;
//                    case R.id.menu_friends:
//                        id = 2;
//                        break;
//                }
//                if(previousPosition != id) {
//                  bind.vp.setCurrentItem(id, false);
//                  previousPosition = id;
//                }

                // you can write as above.
                // I recommend this method. You can change the item order or counts without update code here.
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    // only set item when item changed
                    previousPosition = position;
                    Log.i(TAG, "-----bnve-------- previous item:" + bind.bnve.getCurrentItem() + " current item:" + position + " ------------------");
                    bind.vp.setCurrentItem(position);
                }
                return true;
            }
        });

        // set listener to change the current checked item of bottom nav when scroll view pager
        bind.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "-----ViewPager-------- previous item:" + bind.bnve.getCurrentItem() + " current item:" + position + " ------------------");
                bind.bnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

}
