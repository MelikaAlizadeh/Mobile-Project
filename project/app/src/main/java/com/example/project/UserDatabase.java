package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {
    public static User currentUser;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "userManager";
    public static final String TABLE_NAME = "users";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CITY = "city";
    public static final String KEY_REGION = "region";

    public UserDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE " +
                TABLE_NAME + " ( " +
                KEY_USERNAME + " TEXT, "
                + KEY_PASSWORD + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_CITY + " TEXT, "
                + KEY_REGION + " TEXT"
                + " )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_CITY, " ");
        values.put(KEY_REGION, " ");
        db.insert(TABLE_NAME, null, values);
        currentUser=user;
    }

    public User getUser(String username) {
        User user=new User();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{KEY_USERNAME, KEY_PASSWORD, KEY_EMAIL,KEY_CITY,KEY_REGION},
                KEY_USERNAME + "=?",
                new String[]{String.valueOf(username)},
                null, null, null
        );
        if (cursor != null) {
            cursor.moveToFirst();
            //
            user.setUsername(username);
            user.setPassword(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setCity(cursor.getString(3));
            user.setRegion(cursor.getString(4));
            //
        }
        cursor.close();
        return user;
    }

    public List<User> getAllUsers() {
        List<User> contactList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        String strSQL = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(strSQL, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                contactList.add(user);

            } while (cursor.moveToNext());

        }
        cursor.close();
        return contactList;
    }


    public int getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int result = 0;
        String strSQL = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(strSQL, null);
        result = cursor.getCount();
        cursor.close();
        return result;
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,
                KEY_USERNAME+ "=?",
                new String[]{user.getUsername()});
    }
}
