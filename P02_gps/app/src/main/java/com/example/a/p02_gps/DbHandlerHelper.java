package com.example.a.p02_gps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2016-07-28.
 */
public class DbHandlerHelper extends SQLiteOpenHelper {
    public DbHandlerHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE loc (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "latitude REAL, longitude REAL, timestamp INTEGER );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
