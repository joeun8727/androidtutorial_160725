package com.example.a.a07_listview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        public MyData(int imgId, String strTitle, String strDesc) {
            this.imgId = imgId;
            this.strTitle = strTitle;
            this.strDesc = strDesc;
        }

        int imgId;
        String strTitle;
        String strDesc;
    }
    ArrayList<MyData> list = new ArrayList<MyData>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i =0; i< 30; i++){
            list.add(new MyData(R.mipmap.ic_launcher,"title:" + i , "desc:" + i));
        }

    }
}
