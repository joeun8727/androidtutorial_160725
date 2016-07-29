package com.example.a.a25_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selectedTextView, workingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedTextView = (TextView) findViewById(R.id.selectedTextView);
        workingTextView = (TextView) findViewById(R.id.workingTextView);

        Button zeroButton = (Button) findViewById(R.id.zeroBtn);
        Button oneButton = (Button) findViewById(R.id.oneBtn);
        Button enterButton = (Button) findViewById(R.id.enterBtn);
    }


}
