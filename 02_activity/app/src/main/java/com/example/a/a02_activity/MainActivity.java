package com.example.a.a02_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLoginClick(View v){
        EditText myEdit = (EditText) findViewById(R.id.editID);
        String id = myEdit.getText().toString();
        Toast.makeText(MainActivity.this, "id: " + id, Toast.LENGTH_SHORT).show();
    }

}
