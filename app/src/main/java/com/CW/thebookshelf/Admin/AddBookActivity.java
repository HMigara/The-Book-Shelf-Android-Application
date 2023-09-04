package com.CW.thebookshelf.Admin;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.CW.thebookshelf.JavaClass.AddBook;
import com.CW.thebookshelf.R;
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

import java.text.DateFormat;
import java.util.Calendar;

public class AddBookActivity extends AppCompatActivity {

    private final  int reqCode =100;
    ImageView cover;
    FloatingActionButton addbtn;
    Button btnAdd;
    EditText bookName,bookDiscriptin,bookISBN,bookPrice,numberOfBooks,bookAuthor,Category;
    String ImageURL;
    Uri uri;

    public void onBackPressed() {
        Intent intent = new Intent(AddBookActivity.this, AdminHomeActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // getting all the data to the inside variable
        cover = findViewById(R.id.imgBookCover);
        addbtn = findViewById(R.id.floatingActionButton);
        bookName = findViewById(R.id.etBookName);
        bookDiscriptin = findViewById(R.id.etBookDescription);
        bookISBN = findViewById(R.id.etISBN);
        bookPrice = findViewById(R.id.etPrice);
        numberOfBooks = findViewById(R.id.etCount);
        bookAuthor = findViewById(R.id.etAuthor);
        btnAdd = findViewById(R.id.btnaddbook);
        Category = findViewById(R.id.etCategory);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBookData();
            }
        });

        //to open the gallery when click the add button
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setType("image/*");
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,reqCode);
            }
        });
    }
    private void insertBookData()
    {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Books_Cover_Image")
                .child(uri.getLastPathSegment());

       AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AddBookActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.save_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();



        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                ImageURL = urlImage.toString();
                UplodeData();
                dialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });

    }
    public void UplodeData(){

        String bName =bookName.getText().toString();
        String bDiscript = bookDiscriptin.getText().toString();
        String bisbn = bookISBN.getText().toString();
        String bprice = bookPrice.getText().toString();
        String bAuthor = bookAuthor.getText().toString();
        String bcount = numberOfBooks.getText().toString();
        String bCategory = Category.getText().toString();
        String imgurl = ImageURL.toString();

        AddBook addbook = new AddBook(bName,bDiscript,bisbn,bprice,bcount,bAuthor,bCategory,imgurl);

        String currentData = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());


        FirebaseDatabase.getInstance().getReference("Book").child(currentData)
                .setValue(addbook).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddBookActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            clearFields();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddBookActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    //for set the selected image to the image view in the page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            //Toast.makeText(this, String.valueOf(requestCode), Toast.LENGTH_SHORT).show();
            if (requestCode==reqCode){
                cover.setImageURI(data.getData());
                uri = data.getData();
            }else {
                Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //clear all the fields after submit data
    private void clearFields() {

        cover.setImageBitmap(null);
        bookName.setText("");
        bookName.setText("");
        bookDiscriptin.setText("");
        bookISBN.setText("");
        bookPrice.setText("");
        numberOfBooks.setText("");
        bookAuthor.setText("");
        Category.setText("");
    }
}