package com.example.carevaluate.MyUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.carevaluate.R;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String CREATE_USER = "create table User("
            + "account VARCHAR(50) primary key, "
            + "password VARCHAR(50))";

    public static final String CREATE_CAR = "create table Car("
            + "id int(11) primary key, "
            + "brand VARCHAR(255),"
            + "model VARCHAR(255),"
            + "price double,"
            + "age double,"
            + "distance double,"
            + "description VARCHAR(255),"
            + "image int(15))";

    public static final String CREATE_RECORD = "create table Record("
            + "account VARCHAR(50),"
            + "id int(11),"
            + "foreign key(account) references User(account),"
            + "foreign key(id) references Car(id))";

    public static final String CREATE_COLLECT = "create table Collect("
            + "account VARCHAR(50),"
            + "id int(11),"
            + "foreign key(account) references User(account),"
            + "foreign key(id) references Car(id))";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_CAR);
        db.execSQL(CREATE_RECORD);
        db.execSQL(CREATE_COLLECT);

        for (Car car : CAR_DATA_SET){
            ContentValues values = new ContentValues();
            values.put("id", car.id);
            values.put("brand", car.brand);
            values.put("model", car.model);
            values.put("price", car.price);
            values.put("age", car.age);
            values.put("distance", car.distance);
            values.put("description", car.description);
            values.put("image", car.image);
            db.insert("Car", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public static final Car[] CAR_DATA_SET = new Car[] {
            new Car(1,"奔驰","C级 C200L", 17.5, 2016, 6.8, "2.0T 自动（国V）", R.drawable.car_1),
            new Car(2,"宝马","3系 320i",17,2015,9.0,"2.0T 自动 汽油 运动设计套装（国V）", R.drawable.car_2),
            new Car(3,"宝马","5系 520Li",9.88,2011,10.0,"2.5L 自动 汽油 典雅型（国IV）", R.drawable.car_3),
            new Car(4,"宝马","5系 525Li",18.58,2014,7.0,"2.0T 自动 汽油 豪华设计套装（国IV）", R.drawable.car_4),
            new Car(5,"宝马","5系 520Li",14.5,2013,7.0,"2.0T 自动 汽油 典雅型（国IV）", R.drawable.car_5),
            new Car(6,"宝马","5系 530Li",17,2013,6.0,"3.0L 自动 汽油 领先型（国IV）", R.drawable.car_6),
            new Car(7,"奥迪","A6L TFSI",13.8,2012,15.6,"2.0T 自动 舒适型（国IV）", R.drawable.car_7),
            new Car(8,"宝马","5系 523Li",10.98,2012,9.0,"2.5L 自动 汽油 领先型（国IV）", R.drawable.car_8),
            new Car(9,"本田","思域",13.3,2019,2.0,"1.5T 自动 劲擎版220TURBO（国VI）", R.drawable.car_9),
            new Car(10,"本田","思域",16.5,2018,2.0,"1.5T 自动 舒适版（国V）", R.drawable.car_10),
            new Car(11,"本田","思域",10.3,2019,3.0,"1.5T 自动 燃擎版220TURBO（国V）", R.drawable.car_11),
            new Car(12,"宾利","飞驰",135.8,2015,4.0,"4.0T 自动 标准版", R.drawable.car_12),
            new Car(13,"宾利","飞驰",142.5,2014,4.0,"4.0T 自动 尊贵版", R.drawable.car_13)
    };
}
