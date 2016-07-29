package com.example.a.p02_gps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import java.text.SimpleDateFormat;
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

    public String selectAll(){
        String str = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("loc", null, null, null, null, null, null, null);
        while(c.moveToNext()){
            double lat = c.getDouble(c.getColumnIndex("latitude"));
            double lon = c.getDouble(c.getColumnIndex("longitude"));
            long timestamp = c.getLong(c.getColumnIndex("timestamp"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            str += "lat : "+ String.format("%.2f",lat) +" lon : "+ String.format("%.2f",lon)
                    +" time : "+ dateFormat.format(new Date(timestamp)) + "\n";
        }
        return str;
    }
}

