package com.example.lab2_mob204.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab2_mob204.database.DatabaseHelper;
import com.example.lab2_mob204.model.TypeBook;
import com.example.lab2_mob204.model.User;

import java.util.ArrayList;
import java.util.List;

public class TypeBookDAO {
    public static final String TABLE_NAME2 = "TypeBook";
    private SQLiteDatabase db, db1;
    private DatabaseHelper databaseHelper;
    private static final String TAG = "TypeBookDAO";

    public static final String SQL_TYPEBOOK = " " +
            "CREATE TABLE TypeBook(matheloaitype text primary key," +
            "tentheloai text, mota text,vitri text);";

    public TypeBookDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
        db1 = databaseHelper.getReadableDatabase();
    }

    public boolean insertTypeBook(TypeBook typeBook) {
        ContentValues values = new ContentValues();

        values.put("matheloaitype", typeBook.getMatheloaitype());
        values.put("tentheloai", typeBook.getTentheloai());
        values.put("mota", typeBook.getMota());
        values.put("vitri", typeBook.getVitri());
        long result = db.insert(TABLE_NAME2, null, values);
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
public boolean isDelete(String book){
        long result =db.delete(TABLE_NAME2,"matheloaitype=?",new String[]{book});

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
    public boolean updateTypeBook(TypeBook typeBook){
        ContentValues values = new ContentValues();
        values.put("matheloaitype", typeBook.getMatheloaitype());
        values.put("tentheloai", typeBook.getTentheloai());
        values.put("mota", typeBook.getMota());
        values.put("vitri", typeBook.getVitri());

        long result = db.update(TABLE_NAME2, values, "matheloaitype" + " =?", new String[]{typeBook.getMatheloaitype()});

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
    public List<TypeBook> selectTypeBook() {
        List<TypeBook> typeBookList = new ArrayList<>();

        String select = "SELECT * FROM " + TABLE_NAME2;

        Cursor cursor = db1.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                TypeBook typeBook = new TypeBook();

                typeBook.setMatheloaitype(cursor.getString(0));
                typeBook.setTentheloai(cursor.getString(1));
                typeBook.setMota(cursor.getString(2));
                typeBook.setVitri(cursor.getString(3));

                typeBookList.add(typeBook);

            } while (cursor.moveToNext());
            cursor.close();

        }
        db1.close();
        return typeBookList;
    }
}
