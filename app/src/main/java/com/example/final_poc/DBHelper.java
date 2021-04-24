package com.example.final_poc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID = "ID";
    private static final String CONTACTS_COLUMN_FAVORITE = "favorite";
    private static User user;

    public DBHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CONTACTS_COLUMN_NAME + " TEXT, " + CONTACTS_COLUMN_EMAIL + " TEXT, "
                + CONTACTS_COLUMN_FAVORITE + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACTS_COLUMN_NAME, user.getName());
        cv.put(CONTACTS_COLUMN_EMAIL, user.getEmail());
        cv.put(CONTACTS_COLUMN_FAVORITE, user.getFavorites());
        long insert = db.insert(USER_TABLE, null, cv);


        if(insert == -1){
            return false;
        }else {

            return true;
        }

    }
    public User getfav(String user_nm){

        String query = "SELECT * FROM " + USER_TABLE;
        //String query = "SELECT name, email, favorite " +
          //      "FROM " + USER_TABLE + " WHERE name=" + user_nm + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String email = cursor.getString(1);
            String fav = cursor.getString(2);

            user = new User(-1, user_nm, email, fav);

        }else{

        }



        cursor.close();
        db.close();
        return user;
    }
    public boolean check(String user){
        String query = "SELECT name FROM "
                + USER_TABLE + " WHERE name = " + user;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            return false;
        }else{
            return true;
        }
    }
}
