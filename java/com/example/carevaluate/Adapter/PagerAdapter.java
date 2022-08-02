package com.example.carevaluate.Adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.carevaluate.Fragment.CarResourceFragment;
import com.example.carevaluate.Fragment.EvaluateFragment;
import com.example.carevaluate.Fragment.HomeFragment;
import com.example.carevaluate.Fragment.MyInfoFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private final  String TAG = "PagerAdapter";
    public final int pageNum = 4;
    public final int page_1 = 0;
    public final int page_2 = 1;
    public final int page_3 = 2;
    public final int page_4 = 3;
    private final HomeFragment fragment_1;
    private final EvaluateFragment fragment_2;
    private final CarResourceFragment fragment_3;
    private final MyInfoFragment fragment_4;

    public PagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        fragment_1 = new HomeFragment();
        fragment_2 = new EvaluateFragment();
        fragment_3 = new CarResourceFragment();
        fragment_4 = new MyInfoFragment();
    }

    @Override
    public int getCount() {
        return pageNum;
    }

    @NonNull
    @Override//重写instantiateItem来实例化页卡
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //通过点击的位置来获取碎片
        Fragment fragment = null;
        switch (position) {
            case page_1:
                fragment = fragment_1 ;
                break;
            case page_2:
                fragment = fragment_2;
                break;
            case page_3:
                fragment = fragment_3;
                break;
            case page_4:
                fragment = fragment_4;
                break;
            default:
                Log.d(TAG, "getItem: errorPage");
                break;
        }
        return fragment;
    }

}










