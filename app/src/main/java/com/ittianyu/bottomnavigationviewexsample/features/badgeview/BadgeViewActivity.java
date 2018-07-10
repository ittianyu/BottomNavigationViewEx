package com.ittianyu.bottomnavigationviewexsample.features.badgeview;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityBadgeViewBinding;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class BadgeViewActivity extends AppCompatActivity {
    private ActivityBadgeViewBinding bind;

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


        // add badge
        addBadgeAt(2, 1);
    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(bind.bnve.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
