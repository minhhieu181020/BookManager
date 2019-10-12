package com.example.lab2_mob204.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab2_mob204.database.DatabaseHelper;
import com.example.lab2_mob204.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static final String TABLE_NAME = "User";
    private SQLiteDatabase db, db1;
    private DatabaseHelper dbHelperUser;
    public static final String TAG = "UserDAO";

    public static final String SQL_USER = "" +
            "CREATE TABLE User(username text primary key," +
            "password text, phone text,hoten text);";

    public UserDAO(Context context) {
        dbHelperUser = new DatabaseHelper(context);
        db = dbHelperUser.getWritableDatabase();
        db1 = dbHelperUser.getReadableDatabase();
    }

    public boolean insertUser(User user) {
        ContentValues values = new ContentValues();

        values.put("username", user.getUsername());
        values.put("password", user.getPass());
        values.put("phone", user.getPhone());
        values.put("hoten", user.getFullname());

        long result = db.insert(TABLE_NAME, null, values);
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
    public boolean isDelete(String name){
        long result = db.delete(TABLE_NAME, "username=?", new String[]{name});
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
    public boolean updateUser(User user){
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("phone", user.getPhone());
        values.put("hoten", user.getFullname());

        long result = db.update(TABLE_NAME, values, "username" + " =?", new String[]{user.getUsername()});

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

    public List<User> selectUser() {

        List<User> userList = new ArrayList<>();

        String select = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db1.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setPass(cursor.getString(1));
                user.setPhone(cursor.getString(2));
                user.setFullname(cursor.getString(3));
                userList.add(user);

            } while (cursor.moveToNext());
            cursor.close();
        }
        db1.close();
        return userList;
    }

public  boolean isChangePassword(User user){

        ContentValues values=new ContentValues();
        values.put("username",user.getUsername());
        values.put("password",user.getPass());
        int result=db.update(TABLE_NAME,values,"username =? ",new String[]{user.getUsername()});
        if (result==-1){
            return false;
        }

        return  true;
}
//    }
    public boolean islogin(User user){
        String sqlSelect = "select username, password from user " +
                "where username=? and password=?";
        String username=user.getUsername();
        String password=user.getPass();
        Cursor cursor=db.rawQuery(sqlSelect,new String[]{username,password});
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
    }
}

