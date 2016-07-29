package com.example.a.p02_gps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by a on 2016-07-28.
 */
public class DbHandler {
    DbHandlerHelper helper;

    public DbHandler(Context context){
        helper = new DbHandlerHelper(context, "location", null, 1);
    }

    public void insert(Location location){
        ContentValues values = new ContentValues();
        values.put("latitude", location.getLongitude());
        values.put("longitude", location.getLatitude());
        values.put("timestamp", (new Date()).getTime());

        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("loc", null, values);
    }

    public void selectAll(TextView v){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("loc", null, null, null, null, null, null, null);
        String str = "";
        while(c.moveToNext()){
            str += c.getString(c.getColumnIndex("latitude"));
        }
        v.setText(str);
    }
}

