package com.example.mad_final_paper_practise.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfro.db";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//
//        String SQL_CREATE_ENTRIES = "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + "(" +
//                UsersMaster.Users._ID + " INTEGER PRIMARY KEY," +
//                UsersMaster.Users.COL_1 + "TEXT," +
//                UsersMaster.Users.COL_2 + "TEXT," +
//                UsersMaster.Users.Col_3 + "TEXT)";

        sqLiteDatabase.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY, username TEXT, password TEXT, type TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE messages(id INTEGER PRIMARY KEY, user TEXT, subject TEXT, message TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");

    }


    public void adinfo(String userName, String password, String type){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("username",userName);
        values.put("password",password);
        values.put("type",type);

        long newRowID = db.insert("users",null,values);

    }


    public Cursor getUserList(String username){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM users WHERE username = ?",new String[]{username});
        return data;
    }

    public void addMessage( String user,String subject, String message){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user",user);
        values.put("subject",subject);
        values.put("message",message);

        long newRowID2 = db.insert("messages",null,values);

    }

    public Cursor getListMessage(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM messages",null);
        return data;
    }

    public Cursor getMessage(String subject){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM messages WHERE subject = ?",new String[] {subject});
        return data;
    }
}
