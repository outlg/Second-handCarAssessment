package com.example.carevaluate.MyUtil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarTable {
    public static List<Car> search(String content, Context context){
        DatabaseHelper dbHelper;
        List<Car> searchRes = new ArrayList<>();
        dbHelper = new DatabaseHelper(context, "CAREVALUATE.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query = "select * from Car where "
                + "brand like ?"
                + " OR model like ?"
                + " OR price = ?"
                + " OR age = ?"
                + " OR distance = ?"
                + " OR description like ?";

        String tmp = "%" + content + "%";
        String[] tmpArray = {tmp, tmp, content, content, content, tmp};
        Cursor cursor = db.rawQuery(query,tmpArray);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String brand = cursor.getString(cursor.getColumnIndex("brand"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                double age = cursor.getDouble(cursor.getColumnIndex("age"));
                double distance = cursor.getDouble(cursor.getColumnIndex("distance"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                int image = cursor.getInt(cursor.getColumnIndex("image"));

                Car curCar = new Car(id, brand, model, price, age, distance, description, image);
                searchRes.add(curCar);
            }while (cursor.moveToNext());
        }

        return searchRes;
    }
}
