package com.example.carevaluate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.carevaluate.R;

public class HelpActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_long_pic);
        ImageView imageView = findViewById(R.id.long_pic);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int image = bundle.getInt("image");
        imageView.setImageResource(image);
    }
}
