package com.ittianyu.bottomnavigationviewexsample.viewbadger;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityViewBadgerBinding;
import com.readystatesoftware.viewbadger.BadgeView;

public class ViewBadgerActivity extends AppCompatActivity {
    private ActivityViewBadgerBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_badger);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_view_badger);

        initView();
    }

    private void initView() {
        // disable all animations
        bind.bnve.enableAnimation(false);
        bind.bnve.enableShiftingMode(false);
        bind.bnve.enableItemShiftingMode(false);

        // add a BadgeView at second icon
        bind.bnve.post(new Runnable() {
            @Override
            public void run() {
                final BadgeView badgeView1 = addBadgeViewAt(1, "1");
                final BadgeView badgeView3 = addBadgeViewAt(3, "99");

                // hide the red circle when click
                bind.bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int position = bind.bnve.getMenuItemPosition(item);
                        switch (position) {
                            case 1:
                                badgeView1.toggle(true);
                                break;
                            case 3:
                                badgeView3.toggle(true);
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }

    /**
     * add a BadgeView on icon at position
     * @param position
     * @return
     */
    private BadgeView addBadgeViewAt(int position, String text) {
        // get position
        ImageView icon = bind.bnve.getIconAt(position);
        int[] pos = new int[2];
        icon.getLocationInWindow(pos);
        // action bar height
        ActionBar actionBar = getSupportActionBar();
        int actionBarHeight = 0;
        if (null != actionBar) {
            actionBarHeight = actionBar.getHeight();
        }
        int x = (int) (pos[0] + icon.getMeasuredWidth() * 0.8f);
        int y = (int) (pos[1] - actionBarHeight - icon.getMeasuredHeight() * 1.2f);

        // create BadgeView: detail for BadgeView click here
        // https://github.com/jgilfelt/android-viewbadger
        BadgeView badge = new BadgeView(ViewBadgerActivity.this, bind.rlRoot);
        badge.setText(text);
        badge.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
        badge.setBadgeMargin(x, y);
        badge.show();

        return badge;
    }
}
