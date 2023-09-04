package com.CW.thebookshelf.JavaClass;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.CW.thebookshelf.Admin.AddBookActivity;

public class AddBook {
    public void setBname(String bname) {
        Bname = bname;
    }

    public void setBdiscription(String bdiscription) {
        Bdiscription = bdiscription;
    }

    public void setBisbn(String bisbn) {
        Bisbn = bisbn;
    }

    public void setBprice(String bprice) {
        Bprice = bprice;
    }

    public void setBcount(String bcount) {
        Bcount = bcount;
    }

    public void setBauthor(String bauthor) {
        Bauthor = bauthor;
    }

    public void setBCategory(String BCategory) {
        this.BCategory = BCategory;
    }

    public void setBimgurl(String bimgurl) {
        Bimgurl = bimgurl;
    }
    public void setKye(String key) {this.key = key;}

    String Bname;
    String Bdiscription;
    String Bisbn;
    String Bprice;
    String Bcount;
    String Bauthor;
    String BCategory;
    String Bimgurl;
    String key;


    public AddBook(String bname, String bdiscription, String bisbn, String bprice, String bcount, String bauthor, String bcategory, String bimgurl ) {
        Bname = bname;
        Bdiscription = bdiscription;
        Bisbn = bisbn;
        Bprice = bprice;
        Bcount = bcount;
        Bauthor = bauthor;
        BCategory = bcategory;
        Bimgurl = bimgurl;

    }

    public String getBname() {return Bname;}

    public String getBdiscription() {
        return Bdiscription;
    }

    public String getBisbn() {
        return Bisbn;
    }

    public String getBprice() {
        return Bprice;
    }

    public String getBcount() {
        return Bcount;
    }

    public String getBauthor() {
        return Bauthor;
    }

    public String getBCategory() {return BCategory;}
    public String getBimgurl() {return Bimgurl;}
    public String getKye() {return key;}



    public AddBook() {

    }
}
