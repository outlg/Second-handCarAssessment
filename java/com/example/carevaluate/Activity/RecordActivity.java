package com.example.carevaluate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carevaluate.Adapter.CarAdapter;
import com.example.carevaluate.MainActivity;
import com.example.carevaluate.MyUtil.Car;
import com.example.carevaluate.MyUtil.RecordTable;
import com.example.carevaluate.MyUtil.State;
import com.example.carevaluate.R;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Button back = findViewById(R.id.record_back);
        TextView clear = findViewById(R.id.record_clear);
        RecyclerView carRecyclerView = (RecyclerView) findViewById(R.id.record_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        carRecyclerView.setLayoutManager(layoutManager);

        List<Car> records = new ArrayList<>(RecordTable.get(State.account, this));
        CarAdapter carAdapter = new CarAdapter(this, records);
        carAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent();
            intent.setClass(this, CarInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", records.get(position).getId());
            bundle.putInt("image", records.get(position).getImage());
            bundle.putString("brandModel",records.get(position).getBrand() + " " + records.get(position).getModel());
            bundle.putDouble("price", records.get(position).getPrice());
            bundle.putDouble("age",records.get(position).getAge());
            bundle.putDouble("distance",records.get(position).getDistance());
            bundle.putString("description",records.get(position).getDescription());
            bundle.putString("activity", "RecordActivity");
            intent.putExtras(bundle);
            startActivity(intent);
        });
        carRecyclerView.setAdapter(carAdapter);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(RecordActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        clear.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(RecordActivity.this);
            builder.setTitle("警告");
            builder.setMessage("您确定清空浏览记录吗？");
            builder.setCancelable(false);
            builder.setNegativeButton("取消", (dialog, which) -> {});
            builder.setPositiveButton("确定", (dialog, which) -> {
            RecordTable.delete(State.account, this);

            List<Car> recordsEmpty = new ArrayList<>(RecordTable.get(State.account, this));
            CarAdapter recordsEmptyAdapter = new CarAdapter(this, recordsEmpty);
            recordsEmptyAdapter.setOnItemClickListener((view, position) -> {
                Intent intent = new Intent();
                intent.setClass(this, CarInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", records.get(position).getId());
                bundle.putInt("image", recordsEmpty.get(position).getImage());
                bundle.putString("brandModel",recordsEmpty.get(position).getBrand() + " " + recordsEmpty.get(position).getModel());
                bundle.putDouble("price", recordsEmpty.get(position).getPrice());
                bundle.putDouble("age",recordsEmpty.get(position).getAge());
                bundle.putDouble("distance",recordsEmpty.get(position).getDistance());
                bundle.putString("description",recordsEmpty.get(position).getDescription());
                bundle.putString("activity", "RecordActivity");
                intent.putExtras(bundle);
                startActivity(intent);
            });

            carRecyclerView.setAdapter(recordsEmptyAdapter);

            });
            builder.show();
        });
    }

}
