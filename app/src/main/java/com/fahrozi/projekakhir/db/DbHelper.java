package com.fahrozi.projekakhir.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fahrozi.projekakhir.model.Masyarakat;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmasyarakat";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STD = "masyarakat";
    private static final String KEY_ID = "id";
    private static final String KEY_NIK = "nik";
    private static final String KEY_NAMA = "nama";

    private static final String CREATE_TABLE_MASYARAKAT = "CREATE TABLE "
            + TABLE_STD + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NIK + " TEXT, " + KEY_NAMA + " TEXT );";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MASYARAKAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STD);
        onCreate(db);
    }

    public long addUserDetail(String nik, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIK, nik);
        values.put(KEY_NAMA, nama);
        long insert = db.insert(TABLE_STD, null, values);
        return insert;
    }

    @SuppressLint("Range")
    public ArrayList<Masyarakat> getAllUsers() {
        ArrayList<Masyarakat> userModelArrayList = new ArrayList<Masyarakat>();
        String selectQuery = "SELECT * FROM " + TABLE_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Masyarakat std = new Masyarakat();
                std.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                std.setNIK(c.getString(c.getColumnIndex(KEY_NIK)));
                std.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));
                userModelArrayList.add(std);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STD, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int updateUser(int id, String nik, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIK, nik);
        values.put(KEY_NAMA, nama);
        return db.update(TABLE_STD, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
}