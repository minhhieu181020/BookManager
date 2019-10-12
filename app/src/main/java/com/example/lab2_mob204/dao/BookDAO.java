package com.example.lab2_mob204.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab2_mob204.database.DatabaseHelper;
import com.example.lab2_mob204.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static final String TABLE_NAME = "Book";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelperBook;
    public static final String TAG = "BookDAO";


    public static final String SQL_BOOK = "" +
            "CREATE TABLE Book(masach text primary key," +
            "matheloai text, tieude text,tacgia text,nhaxuatban text,giabia text,soluong text);";

    public BookDAO(Context context) {
        dbHelperBook = new DatabaseHelper(context);
        db = dbHelperBook.getWritableDatabase();
    }

    public boolean insertBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("masach", book.getMasach());
        values.put("matheloai", book.getMatheloai());
        values.put("tieude", book.getTieude());
        values.put("tacgia", book.getTacgia());
        values.put("nhaxuatban", book.getNhaxuatban());
        values.put("giabia", book.getGiabia());
        values.put("soluong", book.getSoluong());

        long result = db.insert(TABLE_NAME, null, values);
        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG,e.toString());
            return false;
        }

        return true;
    }

    public List<Book> selectBook() {
        List<Book> bookList = new ArrayList<>();
        String select = " SELECT * FROM " + TABLE_NAME;

//        Cursor cursor = db1.rawQuery(select, null);
        Cursor cursor=db.rawQuery(select,null);
        Log.e("abc",cursor.getCount()+"");
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setMasach(cursor.getString(0));
                book.setMatheloai(cursor.getString(1));
                book.setTieude(cursor.getString(2));
                book.setTacgia(cursor.getString(3));
                book.setNhaxuatban(cursor.getString(4));
                book.setGiabia(cursor.getString(5));
                book.setSoluong(cursor.getString(6));
                bookList.add(book);
            } while (cursor.moveToNext());
            cursor.close();


        }
        db.close();
        return bookList;
    }
    public boolean updateBook(Book book){
        ContentValues values=new ContentValues();
        values.put("masach",book.getMasach());

        values.put("matheloai",book.getMatheloai());
        values.put("tieude",book.getTieude());
        values.put("tacgia",book.getTacgia());
        values.put("nhaxuatban",book.getNhaxuatban());
        values.put("soluong",book.getSoluong());
        values.put("giabia",book.getGiabia());
        long result=db.update(TABLE_NAME,values,"masach " + " =? ",new String[]{book.getMasach()});
        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "UPDATE: " + e.toString());
            return false;
        }
        return true;

    }
    public boolean isDelete(String name){
        long result = db.delete(TABLE_NAME, "masach=?", new String[]{name});
        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
            return false;
        }
        return true;

    }
}
