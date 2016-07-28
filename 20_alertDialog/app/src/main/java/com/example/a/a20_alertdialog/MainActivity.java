package com.example.a.a20_alertdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final int MY_DIALOG = 100;
    public void onBtnClick(View v){
        //showDialog(MY_DIALOG);
        MyPopup popup = new MyPopup(this);
        popup.show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == MY_DIALOG){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("TITLE");
            builder.setMessage("MESSAGE");
            builder.setCancelable(false); //dialog 바깥부분 눌러도 종료 x
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            return builder.create();
        }
        return super.onCreateDialog(id);
    }
}
