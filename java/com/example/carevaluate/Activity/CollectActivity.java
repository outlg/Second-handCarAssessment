package com.example.carevaluate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carevaluate.Adapter.CarAdapter;
import com.example.carevaluate.MainActivity;
import com.example.carevaluate.MyUtil.Car;
import com.example.carevaluate.MyUtil.CollectTable;
import com.example.carevaluate.MyUtil.State;
import com.example.carevaluate.R;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends BaseActivity{
    List<Car> collection;
    CarAdapter carAdapter;
    RecyclerView carRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        Button back = findViewById(R.id.collect_back);
        carRecyclerView = (RecyclerView) findViewById(R.id.collect_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        carRecyclerView.setLayoutManager(layoutManager);

        setRecyclerView();

        back.setOnClickListener(v -> {
            Intent intent = new Intent(CollectActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void setRecyclerView(){
        collection = new ArrayList<>(CollectTable.get(State.account, this));
        carAdapter = new CarAdapter(this, collection);
        carAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent();
            intent.setClass(this, CarInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", collection.get(position).getId());
            bundle.putInt("image", collection.get(position).getImage());
            bundle.putString("brandModel",collection.get(position).getBrand() + " " + collection.get(position).getModel());
            bundle.putDouble("price", collection.get(position).getPrice());
            bundle.putDouble("age",collection.get(position).getAge());
            bundle.putDouble("distance",collection.get(position).getDistance());
            bundle.putString("description",collection.get(position).getDescription());
            bundle.putString("activity", "CollectActivity");
            intent.putExtras(bundle);
            startActivity(intent);
        });
        carRecyclerView.setAdapter(carAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecyclerView();
    }
}
