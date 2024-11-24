package com.example.sqlitedatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ContactDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Contact";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME
                   + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_PHONE_NO + " TEXT" + ")");


       //  SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

       // sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addContact(String name,String number){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_PHONE_NO,number);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<ContactModel> fetchContact(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

      Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME,null);

      ArrayList<ContactModel> arrayList = new ArrayList<>();

      while (cursor.moveToNext()){

          ContactModel contactModel = new ContactModel();

          contactModel.id = cursor.getInt(0);
          contactModel.name = cursor.getString(1);
          contactModel.phone_no = cursor.getString(2);

          arrayList.add(contactModel);
      }
      return arrayList;
    }


    public void updateData(ContactModel contactModel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PHONE_NO,contactModel.phone_no);

        sqLiteDatabase.update(TABLE_NAME,contentValues,KEY_ID +" = "+contactModel.id,null);
    }

    public void deleteData(int id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME,KEY_ID + " = ? ", new String[]{String.valueOf(id)});

    }
}
