package com.example.carevaluate.Adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carevaluate.MyUtil.Car;
import com.example.carevaluate.MyUtil.SavePic;
import com.example.carevaluate.R;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>  {
    private List<Car> carList;        //一列车辆信息
    Context context;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);//单击
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    public CarAdapter(){}
    public CarAdapter(Context context, List<Car> carList) {
        this.carList = carList;
        this.context=context;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    //使用了ViewHolder就不用频繁的使用findViewById
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textTitle;
        TextView textDetails;
        TextView textPrice;
        LinearLayout carInfo;
        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.car_image);
            textTitle = view.findViewById(R.id.car_title);
            textDetails = view.findViewById(R.id.car_details);
            textPrice = view.findViewById(R.id.car_price);
            carInfo = view.findViewById(R.id.car_info);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将car_item布局嵌入父布局，car_item布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car item = carList.get(position);
        int postionClick = position;

        holder.image.setImageResource(item.getImage());
        String tmp = item.getBrand() + " " + item.getModel() + " " + item.getDescription();
        holder.textTitle.setText(tmp);
        tmp = item.getAge() + "上牌 | " + item.getDistance() + "万公里";
        holder.textDetails.setText(tmp);
        tmp = item.getPrice() + "万";
        holder.textPrice.setText(tmp);

        holder.carInfo.setOnClickListener(v -> {
            if (mOnItemClickListener != null) mOnItemClickListener.onItemClick(v, postionClick);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
