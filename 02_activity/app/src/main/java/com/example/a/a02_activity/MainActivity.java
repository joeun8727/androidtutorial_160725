package com.example.a.a02_activity;

import android.content.Intent;
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

    private final int MY_REQ = 1;
    public void onLoginClick(View v){
        EditText myEdit = (EditText) findViewById(R.id.editID);
        String id = myEdit.getText().toString();
        /*Toast.makeText(MainActivity.this, "id: " + id, Toast.LENGTH_SHORT).show();*/

        Intent intent = new Intent(MainActivity.this, NewActivity.class);
        intent.putExtra("id", id);
        //startActivity(intent);
        startActivityForResult(intent, MY_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQ){
            if(resultCode == RESULT_OK){
                String strResult = data.getStringExtra("res");
                Toast.makeText(MainActivity.this, "result : " + strResult, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
