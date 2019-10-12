package com.example.lab2_mob204.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab2_mob204.dao.BookDAO;
import com.example.lab2_mob204.dao.TypeBookDAO;
import com.example.lab2_mob204.dao.UserDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";

    private static final int version = 3;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.SQL_USER);
        db.execSQL(BookDAO.SQL_BOOK);
        db.execSQL(TypeBookDAO.SQL_TYPEBOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + UserDAO.TABLE_NAME);

        db.execSQL("Drop table if exists " + BookDAO.TABLE_NAME);

        db.execSQL("Drop table if exists " + TypeBookDAO.TABLE_NAME2);
        onCreate(db);


    }
}
