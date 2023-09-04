package com.CW.thebookshelf.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.CW.thebookshelf.R;
import com.CW.thebookshelf.welcomeActivity;

public class DashBordActivity extends AppCompatActivity {

    Button BtnViewBranch,BtnLogOut,BtnBook,BtnSearch;

    public void onBackPressed() {
        Intent intent = new Intent(DashBordActivity.this, welcomeActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);

        BtnViewBranch = findViewById(R.id.Branches);
        BtnLogOut = findViewById(R.id.Logout);
        BtnBook = findViewById(R.id.btnBooks);
        BtnSearch = findViewById(R.id.btnsearch);

        BtnViewBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBordActivity.this, BranchMapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        BtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBordActivity.this, "Loging Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DashBordActivity.this, welcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        BtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBordActivity.this, "Book page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DashBordActivity.this, UserBookDisplayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBordActivity.this, "Search page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DashBordActivity.this, UserSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}