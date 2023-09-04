package com.CW.thebookshelf.Admin;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import com.CW.thebookshelf.JavaClass.DataBaseHelper;
import com.CW.thebookshelf.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminRegisterActivity extends AppCompatActivity {

    EditText fName, lName, uName, Email, Pword, Location, Number;
    Button btnRegister,btnBack;

    DataBaseHelper DBhelper;

    public void onBackPressed() {
        Intent intent = new Intent(AdminRegisterActivity.this, AdminLoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        fName = findViewById(R.id.txtaAdminFirstName);
        lName = findViewById(R.id.txtaAdminLastName);
        uName = findViewById(R.id.txtaAdminuname);
        Pword = findViewById(R.id.txtaAdminPassword);
        Location = findViewById(R.id.txtaAdminLocation);
        Email = findViewById(R.id.txtaAdminEmail);
        Number = findViewById(R.id.txtaAdminMobileNumber);
        btnRegister = findViewById(R.id.btnadminRegister);
        btnBack = findViewById(R.id.btnAdminBack);

        DBhelper = new DataBaseHelper(this);

        AdminRegisterClick();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminRegisterActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void AdminRegisterClick() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FirstName = fName.getText().toString();
                String LastName = lName.getText().toString();
                String UserName = uName.getText().toString();
                String Passsword = Pword.getText().toString();
                String location = Location.getText().toString();
                String email = Email.getText().toString();
                String PhoneNum = Number.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(AdminRegisterActivity.this, "E-Mail field Required !!", Toast.LENGTH_SHORT).show();
                } else if (Passsword.isEmpty()) {
                    Toast.makeText(AdminRegisterActivity.this, "Password Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (location.isEmpty()) {
                    Toast.makeText(AdminRegisterActivity.this, "location Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (UserName.isEmpty()) {
                    Toast.makeText(AdminRegisterActivity.this, "UserName Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (PhoneNum.isEmpty()) {
                    Toast.makeText(AdminRegisterActivity.this, "PhoneNum Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (LastName.isEmpty()) {
                    Toast.makeText(AdminRegisterActivity.this, "LastName Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (FirstName.isEmpty()) {
                    Toast.makeText(AdminRegisterActivity.this, "FirstName Field Required !!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = DBhelper.insertDataAdmin(FirstName, LastName, UserName, email,Passsword,location,PhoneNum);
                    if (isInserted) {
                        Toast.makeText(AdminRegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminRegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    }


                }


            }


        });
    }

}