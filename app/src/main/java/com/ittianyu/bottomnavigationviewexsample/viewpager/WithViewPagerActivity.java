package com.ittianyu.bottomnavigationviewexsample.viewpager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityWithViewPagerBinding;

import java.util.ArrayList;
import java.util.List;

public class WithViewPagerActivity extends AppCompatActivity {
    private ActivityWithViewPagerBinding binding;
    private List<Item> items;
    private VpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_with_view_pager);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_with_view_pager);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        binding.bnve.enableItemShiftingMode(true);
        binding.bnve.enableAnimation(false);
    }

    private void initData() {
        items = new ArrayList<>(3);
        // get transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // create music fragment and add it
        BaseFragment musicFragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.music));
        musicFragment.setArguments(bundle);
        transaction.add(musicFragment, "music");

        // create backup fragment and add it
        BaseFragment backupFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        backupFragment.setArguments(bundle);
        transaction.add(backupFragment, "backup");

        // create friends fragment and add it
        BaseFragment friendsFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.friends));
        friendsFragment.setArguments(bundle);
        transaction.add(friendsFragment, "friends");

        // commit
        transaction.commit();

        // add fragment root view to view pager
        items.add(new Item(R.id.menu_music, musicFragment));
        items.add(new Item(R.id.menu_backup, backupFragment));
        items.add(new Item(R.id.menu_friends, friendsFragment));

        // set adapter
        adapter = new VpAdapter(items);
        binding.vp.setAdapter(adapter);
    }

    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        binding.bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
//                binding.vp.setCurrentItem(id, false);

                // you can write as above.
                // I recommend this method. You can change the item order or count without update code here.
                binding.vp.setCurrentItem(items.indexOf(new Item(item.getItemId(), null)), false);
                return false;
            }
        });

        // set listener to change the current checked item of bottom nav when scroll view pager
        binding.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.bnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends PagerAdapter {
        private List<Item> data;

        public VpAdapter(List<Item> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = data.get(position).fragment.getView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * used for view pager data bean
     */
    private static class Item {
        int resId;
        Fragment fragment;

        public Item(int resId, Fragment fragment) {
            this.resId = resId;
            this.fragment = fragment;
        }

        // rewrite equal and hashcode by resId
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            return resId == item.resId;

        }

        @Override
        public int hashCode() {
            return resId;
        }
    }
}
