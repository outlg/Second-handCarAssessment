package com.example.carevaluate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.carevaluate.MainActivity;
import com.example.carevaluate.R;
import com.example.carevaluate.MyUtil.State;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RelativeLayout logout = findViewById(R.id.log_out);
        Button back = findViewById(R.id.setting_back);

        logout.setOnClickListener(v -> {
            if (State.isLogin){
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("警告");
                builder.setMessage("您确定退出登录吗？");
                builder.setCancelable(false);
                builder.setNegativeButton("取消", (dialog, which) -> {});
                builder.setPositiveButton("确定", (dialog, which) -> {
                    State.isLogin = false;
                    State.account = "";
                    Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
                builder.show();
            }else{
                Toast.makeText(this, "尚未登录", Toast.LENGTH_SHORT).show();
            }

        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}