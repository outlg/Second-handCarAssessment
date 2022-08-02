package com.example.carevaluate.Activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carevaluate.MyUtil.State;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {
    protected static ArrayList<AppCompatActivity> activities = new ArrayList<>();
    protected IntentFilter intentFilter;
    protected NetWorkReceiver myNetWorkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Base", activities.size() + "");
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myNetWorkReceiver = new NetWorkReceiver();
        registerReceiver(myNetWorkReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myNetWorkReceiver != null){
            unregisterReceiver(myNetWorkReceiver);
            myNetWorkReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    protected void finishAll(){
        for (AppCompatActivity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    //断网退出登录
    class NetWorkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //断开网络连接
            if (!(networkInfo != null && networkInfo.isAvailable())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("网络中断");
                builder.setMessage("请重新登录");
                builder.setCancelable(false);//设置为不可点击空白取消对话框
                builder.setNeutralButton("重新登录", (dialogInterface, i) -> {
                    finishAll(); // 销毁所有活动
                    Intent intent1 = new Intent(context, LoginActivity.class);
                    State.isLogin = false;
                    State.account = "";
                    startActivity(intent1);
                });
                AlertDialog dialog = builder.create();      //创建AlertDialog对象
                dialog.show();
            }
        }
    }
}









