package com.example.a.a25_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        View.OnClickListener numberListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view;
                String working = workingTextView.getText().toString();
                String str = button.getText().toString();
                if(working.equals("0")){
                    workingTextView.setText(str);
                }else{
                    workingTextView.setText(working + str);
                }
            }
        };

        Button zeroButton = (Button) findViewById(R.id.zeroBtn);
        Button oneButton = (Button) findViewById(R.id.oneBtn);

        zeroButton.setOnClickListener(numberListener);
        oneButton.setOnClickListener(numberListener);

        Button enterButton = (Button) findViewById(R.id.enterBtn);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String working = workingTextView.getText().toString();
                selectedTextView.setText(working);
                workingTextView.setText("0");
            }
        });
    }
}
