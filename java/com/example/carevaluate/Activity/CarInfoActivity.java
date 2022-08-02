package com.example.carevaluate.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.carevaluate.MainActivity;
import com.example.carevaluate.MyUtil.CollectTable;
import com.example.carevaluate.MyUtil.SavePic;
import com.example.carevaluate.MyUtil.State;
import com.example.carevaluate.R;

public class CarInfoActivity extends BaseActivity{
    Button viewCollect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        ImageView viewImage = findViewById(R.id.car_info_image);
        TextView viewBrandModel = findViewById(R.id.car_info_brand_model);
        TextView viewPrice = findViewById(R.id.car_info_price);
        TextView viewAge = findViewById(R.id.car_info_age);
        TextView viewDistance = findViewById(R.id.car_info_distance);
        TextView viewDescription = findViewById(R.id.car_info_description);
        Button viewBack = findViewById(R.id.car_info_back);
        viewCollect = findViewById(R.id.car_collect);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int id = bundle.getInt("id");
        if (CollectTable.isExist(State.account, id, this)){
            viewCollect.setText("已收藏");
        }

        int image = bundle.getInt("image");
        String brandModel = bundle.getString("brandModel");
        double price = bundle.getDouble("price");
        double age = bundle.getDouble("age");
        double distance = bundle.getDouble("distance");
        String description = bundle.getString("description");
        String activity = bundle.getString("activity");

        viewImage.setImageResource(image);
        viewBrandModel.setText(brandModel);
        viewPrice.setText(price + "万");
        viewAge.setText(age + "年");
        viewDistance.setText(distance + "万公里");
        viewDescription.setText(description);

        viewBack.setOnClickListener(v -> {
            Intent intent1;
            if (activity.equals("RecordActivity"))
                intent1 = new Intent(CarInfoActivity.this, RecordActivity.class);
            else if(activity.equals("CollectActivity"))
                intent1 = new Intent(CarInfoActivity.this, CollectActivity.class);
            else
                intent1 = new Intent(CarInfoActivity.this, MainActivity.class);

            startActivity(intent1);
            finish();
        });


        viewCollect.setOnClickListener(v ->{
            if (State.isLogin){
                //已收藏
                if (CollectTable.isExist(State.account, id, this)){
                    CollectTable.delete(State.account, id, this);
                    viewCollect.setText("＋收藏");
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                }else{//未收藏
                    CollectTable.save(State.account, id, this);
                    viewCollect.setText("已收藏");
                    Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "登录后方可使用此功能", Toast.LENGTH_SHORT).show();
            }
        });

        viewImage.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("保存图片").setMessage("确认保存").setNegativeButton("确认", (dialog, which) ->
            {//相关权限的申请 存储权限
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED
                            || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else{
                        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), image, null);
                        SavePic.saveMyPic(this, bitmap, id + "pic.png");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }).setPositiveButton("取消", (dialog, which) -> {
                }).show();
        });   //图片的点击事件
    }
}
