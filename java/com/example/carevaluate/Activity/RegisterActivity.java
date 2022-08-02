package com.example.carevaluate.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carevaluate.R;
import com.example.carevaluate.MyUtil.DatabaseHelper;

public class RegisterActivity extends BaseActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accountEdit = findViewById(R.id.edit_account_register);
        passwordEdit = findViewById(R.id.edit_password_register);
        Button register = findViewById(R.id.register);
        Button back = findViewById(R.id.register_back);

        dbHelper = new DatabaseHelper(this, "CAREVALUATE.db", null, 1);

        register.setOnClickListener(v ->{
            if(accountEdit.getText().toString().equals("") || passwordEdit.getText().toString().equals("")) {
                Toast.makeText(RegisterActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            }
            else {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String username = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 判断账号是否存在
                Cursor cursor = db.query("User", null, null, null,
                        null, null, null, null);

                boolean accountExist = false;
                //账户已注册
                if(cursor.moveToFirst()) {
                    do{
                        String username_db = cursor.getString(cursor.getColumnIndex("account"));
                        if(username.equals(username_db)) {
                            accountExist = true;
                            Toast.makeText(RegisterActivity.this, "该账户已注册！",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }while (cursor.moveToNext());
                }
                cursor.close();

                //账户未注册
                if(!accountExist) {
                    //插入记录；
                    ContentValues values = new ContentValues();
                    values.put("account",username);
                    values.put("password", password);
                    db.insert("User", null, values);
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        back.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}