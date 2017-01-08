package com.ittianyu.bottomnavigationviewexsample.features.badgeview;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityBadgeViewBinding;

public class BadgeViewActivity extends AppCompatActivity {
    private ActivityBadgeViewBinding bind;
    private BadgeView badgeView1;
    private BadgeView badgeView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_badge_view);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_badge_view);

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
                badgeView1 = addBadgeViewAt(1, "1", BadgeView.SHAPE_OVAL);
                badgeView3 = addBadgeViewAt(3, "99", BadgeView.SHAPE_OVAL);

                // hide the red circle when click
                bind.bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int position = bind.bnve.getMenuItemPosition(item);
                        switch (position) {
                            case 1:
                                toggleBadgeView(badgeView1);
                                break;
                            case 3:
                                toggleBadgeView(badgeView3);
                                break;
                        }
                        return true;
                    }
                });
            }
        });

    }

    /**
     * show or hide badgeView
     * @param badgeView
     */
    private void toggleBadgeView(BadgeView badgeView) {
        badgeView.setVisibility(badgeView.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }

    /**
     * add a BadgeView on icon at position
     * @param position add to which icon
     * @param text the text show on badge
     * @param shape the badge view shape
     * @return
     */
    private BadgeView addBadgeViewAt(int position, String text, int shape) {
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
        int x = (int) (pos[0] + icon.getMeasuredWidth() * 0.7f);
        int y = (int) (pos[1] - actionBarHeight - icon.getMeasuredHeight() * 1.25f);
        // calculate width
        int width = 16 + 4 * (text.length() - 1);
        int height = 16;

        BadgeView badgeView = BadgeFactory.create(this)
                .setTextColor(Color.WHITE)
                .setWidthAndHeight(width, height)
                .setBadgeBackground(Color.RED)
                .setTextSize(10)
                .setBadgeGravity(Gravity.LEFT | Gravity.TOP)
                .setBadgeCount(text)
                .setShape(shape)
//                .setMargin(0, 0, 0, 0)
                .bind(this.bind.rlRoot);
        badgeView.setX(x);
        badgeView.setY(y);
        return badgeView;
    }
}
