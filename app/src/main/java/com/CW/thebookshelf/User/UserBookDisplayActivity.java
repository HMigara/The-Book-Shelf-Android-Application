package com.CW.thebookshelf.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.CW.thebookshelf.Admin.AdminBookViewActivity;
import com.CW.thebookshelf.Admin.AdminHomeActivity;
import com.CW.thebookshelf.JavaClass.AddBook;
import com.CW.thebookshelf.JavaClass.MyAdapter;
import com.CW.thebookshelf.JavaClass.UserAdapter;
import com.CW.thebookshelf.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserBookDisplayActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<AddBook> dataList;
    UserAdapter adapter;
    SearchView searchView;

    public void onBackPressed() {
        Intent intent = new Intent(UserBookDisplayActivity.this, DashBordActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book_display);

        recyclerView = findViewById(R.id.recyclerViewUser);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserBookDisplayActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(UserBookDisplayActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new UserAdapter(UserBookDisplayActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Book");
        dialog.show();
        Toast.makeText(UserBookDisplayActivity.this, "Book arrive", Toast.LENGTH_SHORT).show();
       eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    AddBook dataClass = itemSnapshot.getValue(AddBook.class);

                    dataClass.setKye(itemSnapshot.getKey());

                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

    }
}