package model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import in.teachcoder.app.edirectory.User;

public class DataSoure {
    private final static String LOGTAG = "DbHelper";
    private static final String[] allColumns={
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_NAME,
            DbHelper.COLUMN_DEPARTMNET,
            DbHelper.COLUMN_DESIGNATION,
            DbHelper.COLUMN_LOCATION,
            DbHelper.COLUMN_MOBILE,
            DbHelper.COLUMN_EMAIL_ID
    };
    //db
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;
    //db ends

   public DataSoure(Context context){
       dbHelper = new DbHelper(context);


   }
    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbHelper.getWritableDatabase();

    }
    public void close(){
        Log.i(LOGTAG,"Database Closed");
        dbHelper.close();
    }

    public User create(User user){
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME,user.getName());
        values.put(DbHelper.COLUMN_DEPARTMNET,user.getDepartment());
        values.put(DbHelper.COLUMN_DESIGNATION,user.getDesignation());
        values.put(DbHelper.COLUMN_LOCATION, user.getLocation());
        values.put(DbHelper.COLUMN_MOBILE,user.getMobile());
        values.put(DbHelper.COLUMN_EMAIL_ID, user.getEmail_id());
        long intId = database.insert(DbHelper.TABLE_NAME,null,values);
        user.setId(intId);
    return user;
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<User>();
        Cursor cursor = database.query(DbHelper.TABLE_NAME,allColumns,null,null,null,null,null);
        Log.i(LOGTAG," Rows returned"+cursor.getCount());
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_NAME)));
                user.setDepartment(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_DEPARTMNET)));
                user.setDesignation(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_DESIGNATION)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_MOBILE)));
                user.setEmail_id(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_EMAIL_ID)));
                users.add(user);

            }
        }
        return  users;
    }

}
