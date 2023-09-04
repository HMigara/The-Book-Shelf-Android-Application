package com.CW.thebookshelf.Admin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.CW.thebookshelf.JavaClass.AddBook;
import com.CW.thebookshelf.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateActivity extends AppCompatActivity {

    ImageView ucover;
    Button btnUpdate;
    EditText utitel,udescription,ubookcount,ubookauthor,ubookPrice,uisbn,ucategory;
    String titel,description,bookcount,bookauthor,bookPrice,isbn,category,oldimagUrl,imgeur;
    FloatingActionButton btnaddimg;
    String uImageURL;
    Uri uri;
    String ukey ="";
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        ucover = findViewById(R.id.udetailimg);
        utitel = findViewById(R.id.udetailTitle);
        udescription = findViewById(R.id.udetailsdescrip);
        ubookauthor = findViewById(R.id.udetaisbookauthor);
        uisbn = findViewById(R.id.udetaisIsbn);
        ubookcount= findViewById(R.id.udetaisbookcount);
        ubookPrice = findViewById(R.id.udetaisbookprice);
        ucategory = findViewById(R.id.udetaisCategory);
        btnUpdate = findViewById(R.id.btnupdate);
        btnaddimg =findViewById(R.id.floatingActionButton);


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            ucover.setImageURI(uri);
                        } else {
                            Toast.makeText(UpdateActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            udescription.setText(bundle.getString("bdiscription"));
            utitel.setText(bundle.getString("bname"));
            ubookauthor.setText(bundle.getString("bauthor"));
            ubookcount.setText(bundle.getString("bcount"));
            ubookPrice.setText(bundle.getString("bprice"));
            uisbn.setText(bundle.getString("bisbn"));
            ucategory.setText(bundle.getString("bcategory"));
            ukey = bundle.getString("Key");
            oldimagUrl = bundle.getString("bimgurl");
            Glide.with(UpdateActivity.this).load(bundle.getString("bimgurl")).into(ucover);

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Book").child(ukey);

        btnaddimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UpdateActivity.this, AdminBookViewActivity.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        storageReference = FirebaseStorage.getInstance().getReference().child("Books_Cover_Image").child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();


        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                uImageURL = urlImage.toString();
                updateData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
    public void updateData(){
       // Toast.makeText(this, "update called", Toast.LENGTH_SHORT).show();
        titel= utitel.getText().toString().trim();
        description = udescription.getText().toString().trim();
        bookcount = ubookcount.getText().toString();
        bookauthor = ubookauthor.getText().toString();
        bookPrice = ubookPrice.getText().toString();
        isbn = uisbn.getText().toString();
        category = ucategory.getText().toString();
        imgeur =  String.valueOf(uImageURL);

        AddBook addbook = new AddBook(titel,description,isbn,bookPrice,bookcount,bookauthor,category,imgeur);

        databaseReference.setValue(addbook).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldimagUrl);
                    reference.delete();
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}