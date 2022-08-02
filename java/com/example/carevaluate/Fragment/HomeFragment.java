package com.example.carevaluate.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carevaluate.Activity.LoginActivity;
import com.example.carevaluate.Adapter.CarAdapter;
import com.example.carevaluate.MyUtil.Car;
import com.example.carevaluate.MyUtil.CarTable;
import com.example.carevaluate.MyUtil.State;
import com.example.carevaluate.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView head = view.findViewById(R.id.home_head_img);
        TextView account = view.findViewById(R.id.home_to_login);
        RecyclerView carRecyclerView  = view.findViewById(R.id.home_recycler_view);
        TextView more = view.findViewById(R.id.home_more);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        carRecyclerView.setLayoutManager(layoutManager);


        List<Car> searchRes = CarTable.search("", this.getContext());
        List<Car> recommend = new ArrayList<>();

        for (int i = 0; i < 5 && i < searchRes.size(); i++){
            recommend.add(searchRes.get(i));
        }

        CarAdapter carAdapter = new CarAdapter(view.getContext(), recommend);
        carRecyclerView.setAdapter(carAdapter);

        if(State.isLogin)
            account.setText(State.account);

        head.setOnClickListener(v -> {
            if(!State.isLogin){
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(v -> {
            if(!State.isLogin){
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        more.setOnClickListener(v -> {
            //  先是得到Fragment所在的 Activity，然后在得到管理器对象，
            // 获取并开始事务对象，在进行切换，然后在利用addToBackStack()，最终提交事务
        });

        return view;
    }
}
