package com.CW.thebookshelf.JavaClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DataBasename= "TheBookShelf.DB";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "TheBookShelf.DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create admin table
        db.execSQL("Create table  Admins (ID INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT , lastName TEXT,Username TEXT,Email TEXT,password TEXT,location text,mobileNumber TEXT )");
        //create User table
        db.execSQL("Create table  Users (ID INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT , lastName TEXT,Username TEXT,Email TEXT,password TEXT,location text,mobileNumber TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS  Admins");
        onCreate(db);

    }

    //Register Admin
    public boolean insertDataAdmin(String fname,String Lname,String userName,String Email, String pword, String location,String Number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put("firstName", fname);
        ContentValues.put("lastName", Lname);
        ContentValues.put("Username", userName);
        ContentValues.put("Email", Email);
        ContentValues.put("password", pword);
        ContentValues.put("location", location);
        ContentValues.put("mobileNumber", Number);
        long result = db.insert("Admins", null, ContentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //LOGIN Admin

    public Cursor login_Admin(String Uname, String pword) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Admins WHERE Username = '" + Uname + "' AND password = '" + pword + "'", null);
        return res;
    }

    //Register User
    public boolean insertDataUser(String fname,String Lname,String userName,String Email, String pword, String location,String Number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put("firstName", fname);
        ContentValues.put("lastName", Lname);
        ContentValues.put("Username", userName);
        ContentValues.put("Email", Email);
        ContentValues.put("password", pword);
        ContentValues.put("location", location);
        ContentValues.put("mobileNumber", Number);
        long result = db.insert("Users", null, ContentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //LOGIN User

    public Cursor login_User(String Uname, String pword) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Users WHERE Username = '" + Uname + "' AND password = '" + pword + "'", null);
        return res;
    }

}
