package com.ittianyu.bottomnavigationviewexsample.features.style;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityStyleBinding;

public class StyleActivity extends AppCompatActivity {
    private ActivityStyleBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_style);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_style);

        init();
    }

    private void init() {
        bind.bnveNoAnimation.enableAnimation(false);

        bind.bnveNoShiftingMode.enableShiftingMode(false);

        bind.bnveNoItemShiftingMode.enableItemShiftingMode(false);

        bind.bnveNoText.setTextVisibility(false);

        bind.bnveNoIcon.setIconVisibility(false);

        bind.bnveNoAnimationShiftingMode.enableAnimation(false);
        bind.bnveNoAnimationShiftingMode.enableShiftingMode(false);

        bind.bnveNoAnimationItemShiftingMode.enableAnimation(false);
        bind.bnveNoAnimationItemShiftingMode.enableItemShiftingMode(false);

        bind.bnveNoAnimationShiftingModeItemShiftingMode.enableAnimation(false);
        bind.bnveNoAnimationShiftingModeItemShiftingMode.enableShiftingMode(false);
        bind.bnveNoAnimationShiftingModeItemShiftingMode.enableItemShiftingMode(false);

        bind.bnveNoShiftingModeItemShiftingModeText.enableShiftingMode(false);
        bind.bnveNoShiftingModeItemShiftingModeText.enableItemShiftingMode(false);
        bind.bnveNoShiftingModeItemShiftingModeText.setTextVisibility(false);

        bind.bnveNoAnimationShiftingModeItemShiftingModeText.enableAnimation(false);
        bind.bnveNoAnimationShiftingModeItemShiftingModeText.enableShiftingMode(false);
        bind.bnveNoAnimationShiftingModeItemShiftingModeText.enableItemShiftingMode(false);
        bind.bnveNoAnimationShiftingModeItemShiftingModeText.setTextVisibility(false);

        bind.bnveNoShiftingModeItemShiftingModeAndIcon.enableShiftingMode(false);
        bind.bnveNoShiftingModeItemShiftingModeAndIcon.enableItemShiftingMode(false);
        bind.bnveNoShiftingModeItemShiftingModeAndIcon.setIconVisibility(false);

        bind.bnveNoItemShiftingModeIcon.enableItemShiftingMode(false);
        bind.bnveNoItemShiftingModeIcon.setIconVisibility(false);

        bind.bnveNoAnimationShiftingModeItemShiftingModeIcon.enableAnimation(false);
        bind.bnveNoAnimationShiftingModeItemShiftingModeIcon.enableShiftingMode(false);
        bind.bnveNoAnimationShiftingModeItemShiftingModeIcon.enableItemShiftingMode(false);
        bind.bnveNoAnimationShiftingModeItemShiftingModeIcon.setIconVisibility(false);

        bind.bnveWithPadding.enableAnimation(false);
        bind.bnveWithPadding.enableShiftingMode(false);
        bind.bnveWithPadding.enableItemShiftingMode(false);
        bind.bnveWithPadding.setIconVisibility(false);

        initCenterIconOnly();

        initSmallerText();

        initBiggerIcon();

        initCustomTypeface();

        bind.bnveIconSelector.enableAnimation(false);
    }

    private void initCenterIconOnly() {
        bind.bnveCenterIconOnly.enableAnimation(false);
        bind.bnveCenterIconOnly.enableShiftingMode(false);
        bind.bnveCenterIconOnly.enableItemShiftingMode(false);
        int centerPosition = 2;
        // attention: you must ensure the center menu item title is empty
        // make icon bigger at centerPosition
        bind.bnveCenterIconOnly.setIconSizeAt(centerPosition, 48, 48);
        bind.bnveCenterIconOnly.setItemBackground(centerPosition, R.color.colorGreen);
        bind.bnveCenterIconOnly.setIconTintList(centerPosition,
                getResources().getColorStateList(R.color.selector_item_gray_color));
        bind.bnveCenterIconOnly.setIconCenter(centerPosition);
        // you could set a listener for bnve. and return false when click the center item so that it won't be checked.
        bind.bnveCenterIconOnly.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_add) {
                    Toast.makeText(StyleActivity.this, "add", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });
    }

    private void initSmallerText() {
        bind.bnveSmallerText.enableAnimation(false);
        bind.bnveSmallerText.enableShiftingMode(false);
        bind.bnveSmallerText.enableItemShiftingMode(false);
        // set text size
        bind.bnveSmallerText.setTextSize(8);
    }

    private void initBiggerIcon() {
        bind.bnveBiggerIcon.enableAnimation(false);
        bind.bnveBiggerIcon.enableShiftingMode(false);
        bind.bnveBiggerIcon.enableItemShiftingMode(false);
        // hide text
        bind.bnveBiggerIcon.setTextVisibility(false);
        // set icon size
        int iconSize = 36;
        bind.bnveBiggerIcon.setIconSize(iconSize, iconSize);
        // set item height
        bind.bnveBiggerIcon.setItemHeight(BottomNavigationViewEx.dp2px(this, iconSize + 16));
    }

    private void initCustomTypeface() {
        bind.bnveCustomTypeface.enableAnimation(false);
        bind.bnveCustomTypeface.enableShiftingMode(false);
        bind.bnveCustomTypeface.enableItemShiftingMode(false);
        // set typeface : bold
        bind.bnveCustomTypeface.setTypeface(Typeface.DEFAULT_BOLD);
        // you also could set typeface from file.
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/custom.ttf");
//        bind.bnveCustomTypeface.setTypeface(typeface);
    }

}
