package com.example.a.p02_gps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textView);
        dbHandler = new DbHandler(this);

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("str : ", "lat : " + location.getLatitude() + " lon : " + location.getLongitude() );
                dbHandler.insert(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        };

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, listener);

    }

    public void onBtnClick(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        dbHandler.selectAll(textView);
    }

}
