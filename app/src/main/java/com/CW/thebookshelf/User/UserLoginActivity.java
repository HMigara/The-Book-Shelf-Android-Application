package com.CW.thebookshelf.User;

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

public class UserLoginActivity extends AppCompatActivity {

    EditText uname,password;
    DataBaseHelper DBhelper;
    Button btnlogin,btnback;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserLoginActivity.this, welcomeActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        uname = findViewById(R.id.txtuserlogemail);
        password = findViewById(R.id.txtuserlogpassword);
        btnlogin = findViewById(R.id.btnuserloglogin);
        btnback = findViewById(R.id.btnuserBack);

        DBhelper = new DataBaseHelper(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, welcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserGrandLogin();
            }


            private void UserGrandLogin() {

                String userName = uname.getText().toString();
                String Password = password.getText().toString();

                if (userName.isEmpty()) {
                    Toast.makeText(UserLoginActivity.this, "User Name field Required !!", Toast.LENGTH_SHORT).show();
                } else if (Password.isEmpty()) {
                    Toast.makeText(UserLoginActivity.this, "Password Field Required !!", Toast.LENGTH_SHORT).show();
                }

                else {
                    Cursor res = DBhelper.login_User(userName,Password);
                    if (res.getCount()== 1) {
                        final Intent intent = new Intent(UserLoginActivity.this, DashBordActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(  UserLoginActivity.this, "Invalid Account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
    public void registerUser(View view) {


    Intent intent = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
        startActivity(intent);
        finish();
    }
}