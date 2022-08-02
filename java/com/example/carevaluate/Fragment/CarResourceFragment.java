package com.example.carevaluate.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carevaluate.Activity.CarInfoActivity;
import com.example.carevaluate.R;
import com.example.carevaluate.MyUtil.*;

import java.util.ArrayList;
import java.util.List;
import com.example.carevaluate.Adapter.CarAdapter;

public class CarResourceFragment extends Fragment {
    private View view;
    RecyclerView carRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //绑定相应的碎片布局
        view = inflater.inflate(R.layout.fragment_car_resource, container, false);
        carRecyclerView = view.findViewById(R.id.car_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        carRecyclerView.setLayoutManager(layoutManager);

        List<Car> searchRes = new ArrayList<>(CarTable.search("", this.getContext()));
        CarAdapter carAdapter = new CarAdapter(view.getContext(), searchRes);
        carAdapter.setOnItemClickListener((view, position) -> {
            if (State.isLogin)
                RecordTable.save(State.account, searchRes.get(position).getId(), getContext());

            Intent intent = new Intent();
            intent.setClass(getContext(), CarInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", searchRes.get(position).getId());
            bundle.putInt("image", searchRes.get(position).getImage());
            bundle.putString("brandModel",searchRes.get(position).getBrand() + " " + searchRes.get(position).getModel());
            bundle.putDouble("price", searchRes.get(position).getPrice());
            bundle.putDouble("age",searchRes.get(position).getAge());
            bundle.putDouble("distance",searchRes.get(position).getDistance());
            bundle.putString("description",searchRes.get(position).getDescription());
            bundle.putString("activity", "MainActivity");
            intent.putExtras(bundle);
            startActivity(intent);
        });
        carRecyclerView.setAdapter(carAdapter);

        getList();
        return view;
    }

    public void getList(){
        EditText searchContent = view.findViewById(R.id.car_search_content);
        Button searchBtn = view.findViewById(R.id.car_search_btn);

        searchBtn.setOnClickListener(v->{
            String content = searchContent.getText().toString();
            List<Car> searchRes = CarTable.search(content, this.getContext());
            CarAdapter carAdapter = new CarAdapter(view.getContext(), searchRes);

            if (searchRes.size() == 0){
                Toast.makeText(this.getContext(), "抱歉，未找到你要搜索的内容", Toast.LENGTH_SHORT).show();
            }

            carAdapter.setOnItemClickListener((view, position) -> {
                if (State.isLogin)
                    RecordTable.save(State.account, searchRes.get(position).getId(), getContext());

                Intent intent = new Intent();
                intent.setClass(getContext(), CarInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", searchRes.get(position).getId());
                bundle.putInt("image", searchRes.get(position).getImage());
                bundle.putString("brandModel",searchRes.get(position).getBrand() + " " + searchRes.get(position).getModel());
                bundle.putDouble("price", searchRes.get(position).getPrice());
                bundle.putDouble("age",searchRes.get(position).getAge());
                bundle.putDouble("distance",searchRes.get(position).getDistance());
                bundle.putString("description",searchRes.get(position).getDescription());
                bundle.putString("activity", "MainActivity");
                intent.putExtras(bundle);
                startActivity(intent);
            });

            carRecyclerView.setAdapter(carAdapter);
        });
    }


}
