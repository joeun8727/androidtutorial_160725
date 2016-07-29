package com.example.a.a25_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selectedTextView, workingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedTextView = (TextView) findViewById(R.id.selectedTextView);
        workingTextView = (TextView) findViewById(R.id.workingTextView);

        final View.OnClickListener numberListener = new View.OnClickListener(){
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

        int num = 1;
        TableLayout remoteLayout = (TableLayout) findViewById(R.id.remoteLayout);
        for(int i =2; i<remoteLayout.getChildCount() -1; i++){
            TableRow tableRow = (TableRow) remoteLayout.getChildAt(i);
            for(int j =0; j<tableRow.getChildCount(); j++){
                Button button = (Button) tableRow.getChildAt(j);
                button.setText("" + num);
                num++;
                button.setOnClickListener(numberListener);
            }
        }
        TableRow row = (TableRow) remoteLayout.getChildAt(remoteLayout.getChildCount()-1);
        Button deleteBtn = (Button) row.getChildAt(0);
        deleteBtn.setText("delete");
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });
        Button zeroBtn = (Button) row.getChildAt(1);
        zeroBtn.setText("0");
        zeroBtn.setOnClickListener(numberListener);
        Button enterBtn = (Button) row.getChildAt(2);
        enterBtn.setText("enter");
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTextView.setText(workingTextView.getText().toString());
                workingTextView.setText("0");
            }
        });
    }
}
