package com.example.a.a09_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected String doInBackground(Integer... integers) {
            int startValue = integers[0];
            for(int i=startValue; i<100; i++){

                Log.d("asyncTask", "count : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

        MyAsyncTask task = new MyAsyncTask();
        task.execute(30);
    }
}
