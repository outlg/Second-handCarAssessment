package com.example.carevaluate;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.carevaluate.Activity.BaseActivity;
import com.example.carevaluate.Adapter.PagerAdapter;
import com.example.carevaluate.MyUtil.State;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity {
    public final String[] categories = {"首页", "专家评估", "车源", "我的"};
    public final int[] icons = {
            R.drawable.icon_home, R.drawable.icon_evaluate,
            R.drawable.icon_car_resource, R.drawable.icon_my_info,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使用getSupportFragmentManager()获取fragment实例
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.main_page);
        //关联viewpager和fragmentAdapter
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        //设置ViewPager的加载缓存页面数，防止多次调用onCreate方法
        viewPager.setOffscreenPageLimit(pagerAdapter.pageNum);
        //关联tabLayout和viewPager
        TabLayout tabLayout = findViewById(R.id.bottom_tab);

        for (int i = 0; i < pagerAdapter.pageNum; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < pagerAdapter.pageNum; i++) {
            tabLayout.getTabAt(i).setText(categories[i]);
        }//设置tab的标签
        for (int i = 0; i < pagerAdapter.pageNum; i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }//设置tab的图标
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "调用onResume");
        TextView info_account = findViewById(R.id.info_to_login);
        if (info_account != null){
            if(State.isLogin)
                info_account.setText(State.account);
            else
                info_account.setText("登录");
        }

        TextView home_account = findViewById(R.id.home_to_login);
        if (home_account != null){
            if(State.isLogin)
                home_account.setText(State.account);
            else
                home_account.setText("登录");
        }

        TextView evaluate_account = findViewById(R.id.evaluate_to_login);
        if (evaluate_account != null){
            if(State.isLogin)
                evaluate_account.setText(State.account);
            else
                evaluate_account.setText("登录");
        }
    }

}