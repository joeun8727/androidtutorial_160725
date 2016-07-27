package com.example.a.a13_sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestDBHandler dbHandler = new TestDBHandler(this);
        dbHandler.insert("홍길동", 10, "서울");
        dbHandler.insert("가나다", 11, "인천");
        dbHandler.insert("사람", 12, "부산");

        dbHandler.delete("가나다");
        dbHandler.update("사람", 13);
        dbHandler.selectAll();
    }
}
