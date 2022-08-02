package com.example.carevaluate.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.carevaluate.Activity.HelpActivity;
import com.example.carevaluate.Activity.LoginActivity;
import com.example.carevaluate.MyUtil.State;
import com.example.carevaluate.R;

public class EvaluateFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);

        ImageView head = view.findViewById(R.id.evaluate_head_img);
        TextView account = view.findViewById(R.id.evaluate_to_login);

        ImageView image_czsc = view.findViewById(R.id.evaluate_czsc);
        ImageView iamge_pgjj = view.findViewById(R.id.evaluate_pgjj);
        ImageView image_zxfh = view.findViewById(R.id.evaluate_zxfh);
        ImageView image_dhfh = view.findViewById(R.id.evaluate_dhfh);
        ImageView image_jpgz = view.findViewById(R.id.evaluate_jpgz);

        image_czsc.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setClass(this.getContext(), HelpActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("image",R.drawable.help_czsc);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        iamge_pgjj.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setClass(this.getContext(), HelpActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("image",R.drawable.help_pgjj);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        image_zxfh.setOnClickListener(view1 -> {
            Uri uri = Uri.parse("https://www.lanbenjia.com/business.html#3");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        image_dhfh.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + 123456);
            intent.setData(data);
            startActivity(intent);
        });


        image_jpgz.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setClass(this.getContext(), HelpActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("image",R.drawable.help_jpgz);
            intent.putExtras(bundle);
            startActivity(intent);
        });

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

        return view;
    }
}
