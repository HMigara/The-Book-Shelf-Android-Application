package com.CW.thebookshelf.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.CW.thebookshelf.JavaClass.DataBaseHelper;
import com.CW.thebookshelf.R;


public class UserRegisterActivity extends AppCompatActivity {

    EditText fName, lName, uName, Email, Pword, Location, Number;
    Button btnRegister,btnBack;

    DataBaseHelper DBhelper;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        fName = findViewById(R.id.txtuserFirstName);
        lName = findViewById(R.id.txtuserLastName);
        uName = findViewById(R.id.txtuserUserName);
        Pword = findViewById(R.id.txtuserPassword);
        Location = findViewById(R.id.txtuserLocation);
        Email = findViewById(R.id.txtuserEmail);
        Number = findViewById(R.id.txtuserMobileNumber);
        btnRegister = findViewById(R.id.btnUserregister);
        btnBack = findViewById(R.id.btnUserregiBack);

        DBhelper = new DataBaseHelper(this);

        AdminRegisterClick();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
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
                    Toast.makeText(UserRegisterActivity.this, "E-Mail field Required !!", Toast.LENGTH_SHORT).show();
                } else if (Passsword.isEmpty()) {
                    Toast.makeText(UserRegisterActivity.this, "Password Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (location.isEmpty()) {
                    Toast.makeText(UserRegisterActivity.this, "location Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (UserName.isEmpty()) {
                    Toast.makeText(UserRegisterActivity.this, "UserName Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (PhoneNum.isEmpty()) {
                    Toast.makeText(UserRegisterActivity.this, "PhoneNum Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (LastName.isEmpty()) {
                    Toast.makeText(UserRegisterActivity.this, "LastName Field Required !!", Toast.LENGTH_SHORT).show();
                } else if (FirstName.isEmpty()) {
                    Toast.makeText(UserRegisterActivity.this, "FirstName Field Required !!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = DBhelper.insertDataUser(FirstName, LastName, UserName, email,Passsword,location,PhoneNum);
                    if (isInserted) {
                        Toast.makeText(UserRegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UserRegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    }


                }


            }


        });
    }
    }
