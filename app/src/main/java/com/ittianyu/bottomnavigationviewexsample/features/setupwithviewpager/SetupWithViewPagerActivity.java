package com.ittianyu.bottomnavigationviewexsample.features.setupwithviewpager;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewexsample.common.base.BaseFragment;
import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityWithViewPagerBinding;

import java.util.ArrayList;
import java.util.List;

public class SetupWithViewPagerActivity extends AppCompatActivity {
    private static final String TAG = SetupWithViewPagerActivity.class.getSimpleName();
    private ActivityWithViewPagerBinding bind;
    private VpAdapter adapter;

    // collections
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
//        bind.bnve.enableItemShiftingMode(true);
//        bind.bnve.enableAnimation(false);
    }

    /**
     * create fragments
     */
    private void initData() {
        fragments = new ArrayList<>(3);

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

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        bind.vp.setAdapter(adapter);

        // binding with ViewPager
        bind.bnve.setupWithViewPager(bind.vp);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to do something then item selected
        bind.bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, item.getItemId() + " item was selected-------------------");
                // you can return false to cancel select
                return true;
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
