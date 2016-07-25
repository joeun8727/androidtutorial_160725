package com.example.a.a04_widget2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOk = (Button)findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ok button click", Toast.LENGTH_SHORT).show();
            }
        });
        CheckBox myCheckBox = (CheckBox)findViewById(R.id.myCheckBox);
        myCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "checkBox checked: " + isChecked , Toast.LENGTH_SHORT).show();
            }
        });
        RadioGroup myRadioGroup = (RadioGroup)findViewById(R.id.myRadioGroup);
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1 :
                        Toast.makeText(MainActivity.this, "radio1 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio2 :
                        Toast.makeText(MainActivity.this, "radio2 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio3 :
                        Toast.makeText(MainActivity.this, "radio3 selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
