package com.example.carevaluate.MyUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecordTable {
    public static void save(String account, int carId, Context context){
        DatabaseHelper dbHelper;
        dbHelper = new DatabaseHelper(context, "CAREVALUATE.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account", account);
        values.put("id", carId);
        db.insert("Record", null, values);
    }

    public static List<Car> get(String account, Context context){
        List<Car> records = new ArrayList<>();
        DatabaseHelper dbHelper;
        dbHelper = new DatabaseHelper(context, "CAREVALUATE.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query = "select * from Car where Car.id in " +
                "(select Record.id from Record where Record.account = ?)";
        String[] tmpArray = {account};
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
                records.add(curCar);
            }while (cursor.moveToNext());
        }
        return records;
    }

    public static void delete(String account, Context context){
        DatabaseHelper dbHelper;
        dbHelper = new DatabaseHelper(context, "CAREVALUATE.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("Record", "account = ?", new String[]{account});
    }
}
