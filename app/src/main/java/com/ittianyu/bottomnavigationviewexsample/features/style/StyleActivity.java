package com.ittianyu.bottomnavigationviewexsample.features.style;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityStyleBinding;

public class StyleActivity extends AppCompatActivity {
    private ActivityStyleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_style);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_style);

        init();
    }

    private void init() {
        binding.bnveNoAnimation.enableAnimation(false);

        binding.bnveNoShiftingMode.enableShiftingMode(false);

        binding.bnveNoItemShiftingMode.enableItemShiftingMode(false);

        binding.bnveNoText.setTextVisibility(false);

        binding.bnveNoIcon.setIconVisibility(false);

        binding.bnveNoAnimationShiftingMode.enableAnimation(false);
        binding.bnveNoAnimationShiftingMode.enableShiftingMode(false);

        binding.bnveNoAnimationItemShiftingMode.enableAnimation(false);
        binding.bnveNoAnimationItemShiftingMode.enableItemShiftingMode(false);

        binding.bnveNoAnimationShiftingModeItemShiftingMode.enableAnimation(false);
        binding.bnveNoAnimationShiftingModeItemShiftingMode.enableShiftingMode(false);
        binding.bnveNoAnimationShiftingModeItemShiftingMode.enableItemShiftingMode(false);

        binding.bnveNoShiftingModeItemShiftingModeText.enableShiftingMode(false);
        binding.bnveNoShiftingModeItemShiftingModeText.enableItemShiftingMode(false);
        binding.bnveNoShiftingModeItemShiftingModeText.setTextVisibility(false);

        binding.bnveNoAnimationShiftingModeItemShiftingModeText.enableAnimation(false);
        binding.bnveNoAnimationShiftingModeItemShiftingModeText.enableShiftingMode(false);
        binding.bnveNoAnimationShiftingModeItemShiftingModeText.enableItemShiftingMode(false);
        binding.bnveNoAnimationShiftingModeItemShiftingModeText.setTextVisibility(false);

        binding.bnveNoShiftingModeItemShiftingModeAndIcon.enableShiftingMode(false);
        binding.bnveNoShiftingModeItemShiftingModeAndIcon.enableItemShiftingMode(false);
        binding.bnveNoShiftingModeItemShiftingModeAndIcon.setIconVisibility(false);

        binding.bnveNoItemShiftingModeIcon.enableItemShiftingMode(false);
        binding.bnveNoItemShiftingModeIcon.setIconVisibility(false);

        binding.bnveNoAnimationShiftingModeItemShiftingModeIcon.enableAnimation(false);
        binding.bnveNoAnimationShiftingModeItemShiftingModeIcon.enableShiftingMode(false);
        binding.bnveNoAnimationShiftingModeItemShiftingModeIcon.enableItemShiftingMode(false);
        binding.bnveNoAnimationShiftingModeItemShiftingModeIcon.setIconVisibility(false);

    }
}
