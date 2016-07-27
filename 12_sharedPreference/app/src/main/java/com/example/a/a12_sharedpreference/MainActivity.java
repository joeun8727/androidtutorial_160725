package com.example.a.a12_sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("MySetting", 0);
        String value = preferences.getString("name", "NO DATA");
        Toast.makeText(MainActivity.this, "VALUE : " + value, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = getSharedPreferences("MySetting", 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name", "홍길동");
        editor.commit();
    }
}
