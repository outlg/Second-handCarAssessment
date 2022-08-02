package com.example.carevaluate.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.carevaluate.Activity.CollectActivity;
import com.example.carevaluate.Activity.IntroActivity;
import com.example.carevaluate.Activity.LoginActivity;
import com.example.carevaluate.Activity.RecordActivity;
import com.example.carevaluate.Activity.SettingActivity;
import com.example.carevaluate.R;
import com.example.carevaluate.MyUtil.State;

public class MyInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        TextView textView_account = view.findViewById(R.id.info_to_login);
        RelativeLayout login_block = view.findViewById(R.id.login_block);
        TextView setting = view.findViewById(R.id.my_setting);
        ImageView record = view.findViewById(R.id.info_record);
        ImageView collect = view.findViewById(R.id.info_collect);
        ImageView shareApp = view.findViewById(R.id.info_share);
        ImageView introApp = view.findViewById(R.id.info_intro);

        if(State.isLogin)
            textView_account.setText(State.account+"\n个人账户");

        login_block.setOnClickListener(v -> {
            if(!State.isLogin){
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(v -> {
            Intent intent=new Intent(v.getContext(), SettingActivity.class);
            startActivity(intent);
        });

        record.setOnClickListener(v -> {
            if (State.isLogin){
                Intent intent=new Intent(v.getContext(), RecordActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getContext(), "登录后方可使用此功能", Toast.LENGTH_SHORT).show();
            }
        });

        collect.setOnClickListener(v -> {
            if (State.isLogin){
                Intent intent=new Intent(v.getContext(), CollectActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getContext(), "登录后方可使用此功能", Toast.LENGTH_SHORT).show();
            }
        });

        shareApp.setOnClickListener(v -> {
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String msg = "二手车估价App，就上蓝本价：https://www.lanbenjia.com/";
            intent.putExtra(Intent.EXTRA_TEXT,msg);
            startActivity(Intent.createChooser(intent,"二手车估价"));
        });

        introApp.setOnClickListener(v -> {
            Intent intent=new Intent(v.getContext(), IntroActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
