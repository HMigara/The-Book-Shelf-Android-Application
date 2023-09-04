package com.CW.thebookshelf.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CW.thebookshelf.JavaClass.DataBaseHelper;
import com.CW.thebookshelf.R;
import com.CW.thebookshelf.welcomeActivity;

public class AdminLoginActivity extends AppCompatActivity {

    EditText uname,password;
    DataBaseHelper DBhelper;
    Button btnlogin,btnBack;

    public void onBackPressed() {
        Intent intent = new Intent(AdminLoginActivity.this, welcomeActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        uname = findViewById(R.id.txtAdminloginUname);
        password = findViewById(R.id.txtAdminloginpassword);
        btnlogin = findViewById(R.id.btnadminlogin);
        btnBack = findViewById(R.id.btnadminBack);

        DBhelper = new DataBaseHelper(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, welcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 AdminGrandLogin();
            }


            private void AdminGrandLogin() {

                String userName = uname.getText().toString();
                String Password = password.getText().toString();

                if (userName.isEmpty()) {
                    Toast.makeText(AdminLoginActivity.this, "User Name field Required !!", Toast.LENGTH_SHORT).show();
                } else if (Password.isEmpty()) {
                    Toast.makeText(AdminLoginActivity.this, "Password Field Required !!", Toast.LENGTH_SHORT).show();
                }

                else {
                    Cursor res = DBhelper.login_Admin(userName,Password);
                    if (res.getCount()== 1) {
                        final Intent intent = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(  AdminLoginActivity.this, "Invalid Account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void registerUser(View view) {

            Intent intent = new Intent(AdminLoginActivity.this, AdminRegisterActivity.class);
            startActivity(intent);
            finish();
    }
}