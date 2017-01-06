package com.ittianyu.bottomnavigationviewexsample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityMainBinding;
import com.ittianyu.bottomnavigationviewexsample.setupwithviewpager.SetupWithViewPagerActivity;
import com.ittianyu.bottomnavigationviewexsample.style.StyleActivity;
import com.ittianyu.bottomnavigationviewexsample.viewbadger.ViewBadgerActivity;
import com.ittianyu.bottomnavigationviewexsample.viewpager.WithViewPagerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        binding.btnStyle.setOnClickListener(this);
        binding.btnWithViewPager.setOnClickListener(this);
        binding.btnSetupWithViewPager.setOnClickListener(this);
        binding.btnViewBadger.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_style:
                startActivity(new Intent(this, StyleActivity.class));
                break;
            case R.id.btn_with_view_pager:
                startActivity(new Intent(this, WithViewPagerActivity.class));
                break;
            case R.id.btn_setup_with_view_pager:
                startActivity(new Intent(this, SetupWithViewPagerActivity.class));
                break;
            case R.id.btn_view_badger:
                startActivity(new Intent(this, ViewBadgerActivity.class));
                break;

        }
    }
}
