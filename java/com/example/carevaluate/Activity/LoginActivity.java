package com.example.carevaluate.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carevaluate.MainActivity;
import com.example.carevaluate.R;
import com.example.carevaluate.MyUtil.DatabaseHelper;
import com.example.carevaluate.MyUtil.State;

public class LoginActivity extends BaseActivity {
    public SharedPreferences pref;//构建对象
    public SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private DatabaseHelper dbHelper;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        accountEdit = findViewById(R.id.edit_account_login);
        passwordEdit = findViewById(R.id.edit_password_login);
        rememberPass = findViewById(R.id.remember_passwd);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);
        Button back = findViewById(R.id.login_back);

        //查看是否勾选记住密码
        boolean isRemember = pref.getBoolean("remember_passwd", false);
        if (isRemember) {
            // 将账号和密码都填到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        //新建数据库
        dbHelper = new DatabaseHelper(this, "CAREVALUATE.db", null, 1);

        login.setOnClickListener(v -> {
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            editor = pref.edit();

            Cursor cursor = db.query("User", null, null, null, null, null, null, null);

            boolean accountExist = false;//用户是否存在
            boolean loginSuccess = false;//登录是否成功

            // 遍历数据库，判断账户密码是否匹配
            if(cursor.moveToFirst()) {
                do{
                    String username_db = cursor.getString(cursor.getColumnIndex("account"));
                    String password_db = cursor.getString(cursor.getColumnIndex("password"));
                    if(account.equals(username_db)){
                        accountExist = true;
                        if(password.equals(password_db)) {
                            loginSuccess = true;
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            State.isLogin = true;
                            cursor.close();
                            if (rememberPass.isChecked()) { // 检查复选框是否被选中
                                saveAccount(true, account, password);
                            } else {
                                editor.putBoolean("remember_passwd", false);
                            }
                            editor.commit();
                            State.account = account;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                            finish();
                            break;
                        }
                    }
                }while (cursor.moveToNext());
            }

            //如果不存在账户
            if (!accountExist){
                Toast.makeText(LoginActivity.this, "账户不存在，请先注册", Toast.LENGTH_SHORT).show();
            }else if (!loginSuccess){
                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void saveAccount(boolean rpw, String account, String password){
        editor.putBoolean("remember_passwd", rpw);
        editor.putString("account", account);
        editor.putString("password", password);
    }
}






