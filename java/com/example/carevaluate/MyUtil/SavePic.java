package com.example.carevaluate.MyUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavePic {
    public static void saveMyPic(Context activity, Bitmap bitmap, String picName) {
        //设置图片名称，要保存png，后缀就是png，要保存jpg，后缀就用jpg
        File f = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(f.getPath() + "/"+picName);//创建文件
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);//文件输出流
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();//写入，这里会卡顿，因为图片较大
            fileOutputStream.close();//关闭写入流
            Toast.makeText(activity, "保存成功，请您到 相册/图库 中查看", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(activity, "保存失败", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(activity, "保存失败", Toast.LENGTH_SHORT).show();
        }
        activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));
    }
}
