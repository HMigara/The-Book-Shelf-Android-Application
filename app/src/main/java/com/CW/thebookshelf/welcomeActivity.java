package com.CW.thebookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.CW.thebookshelf.Admin.AdminLoginActivity;
import com.CW.thebookshelf.User.UserLoginActivity;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    //for the user button to open user login
    public void user(View view) {
        Intent userintent = new Intent(welcomeActivity.this, UserLoginActivity.class);
        startActivity(userintent);
        finish();
    }

    //for the admin button to open admin login
    public void admin(View view) {

        Intent adminintent = new Intent(welcomeActivity.this, AdminLoginActivity.class);
        startActivity(adminintent);
        finish();
    }
}