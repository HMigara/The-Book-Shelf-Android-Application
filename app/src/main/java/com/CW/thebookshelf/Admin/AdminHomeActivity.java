package com.CW.thebookshelf.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.CW.thebookshelf.R;
import com.CW.thebookshelf.UpdateBookActivity;

public class AdminHomeActivity extends AppCompatActivity {

    Button add,view,branchInfo;

    public void onBackPressed() {
        Intent intent = new Intent(AdminHomeActivity.this, AdminLoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        add = findViewById(R.id.btnAddBook);
        view = findViewById(R.id.btnViewBook);
        branchInfo = findViewById(R.id.btnUpdateBranchInfo);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add book page intent
                Intent intent = new Intent(AdminHomeActivity.this, AddBookActivity.class);
                startActivity(intent);
                finish();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminBookViewActivity.class);
                startActivity(intent);
                finish();
            }
        });

        branchInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //branch info page intent
            }
        });
    }
}