package com.CW.thebookshelf.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.CW.thebookshelf.R;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailsActivity extends AppCompatActivity {

    TextView titel,description,bookcount,bookauthor,bookPrice,isbn,category;
    ImageView dImg;
    FloatingActionButton deleteButton, editButton;;
    String key="";
    String ImgURL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dImg = findViewById(R.id.detailimg);
        titel = findViewById(R.id.detailTitle);
        description = findViewById(R.id.detailsdescrip);
        bookauthor = findViewById(R.id.detaisbookauthor);
        isbn = findViewById(R.id.detaisIsbn);
        bookcount= findViewById(R.id.detaisbookcount);
        bookPrice = findViewById(R.id.detaisbookprice);
        deleteButton = findViewById(R.id.deleteButtonn);
        editButton = findViewById(R.id.editButtonn);
        category = findViewById(R.id.detaisCategory);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            /*Toast.makeText(this, String.valueOf(isbn), Toast.LENGTH_SHORT).show();*/
            /*description.setText("haritha test");
            titel.setText("haritha test");*/
            description.setText(bundle.getString("bdiscription"));
            titel.setText(bundle.getString("bname"));
            bookauthor.setText(bundle.getString("bauthor"));
            bookcount.setText(bundle.getString("bcount"));
            bookPrice.setText(bundle.getString("bprice"));
            isbn.setText(bundle.getString("bisbn"));
            category.setText(bundle.getString("bcategory"));
            key = bundle.getString("key");
            ImgURL = bundle.getString("bimgurl");
            Glide.with(this).load(bundle.getString("bimgurl")).into(dImg);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, String.valueOf(key), Toast.LENGTH_SHORT).show();
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Book");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(ImgURL);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(DetailsActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DetailsActivity.this, AdminBookViewActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DetailsActivity.this, "Failed to delete from database", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailsActivity.this, "Failed to delete from storage", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titel != null && description != null && category != null &&
                        ImgURL != null && bookauthor != null && bookcount != null &&
                        bookPrice != null && isbn != null && key != null) {

                    Toast.makeText(DetailsActivity.this, "All data arraived.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailsActivity.this, UpdateActivity.class)
                            .putExtra("bname", titel.getText().toString())
                            .putExtra("bdiscription", description.getText().toString())
                            .putExtra("bcategory", category.getText().toString())
                            .putExtra("bimgurl", ImgURL)
                            .putExtra("bauthor", bookauthor.getText().toString())
                            .putExtra("bcount", String.valueOf(bookcount.getText())) // Convert to string
                            .putExtra("bprice", String.valueOf(bookPrice.getText())) // Convert to string
                            .putExtra("bisbn", String.valueOf(isbn.getText())) // Convert to string
                            .putExtra("Key", key);
                    startActivity(intent);
                } else {

                    Toast.makeText(DetailsActivity.this, "Some data is missing or null.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}