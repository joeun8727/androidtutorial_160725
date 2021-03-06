package com.example.a.a07_listview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inf = getLayoutInflater();
                convertView = inf.inflate(R.layout.item_view, null);
            }
            MyData data = list.get(position);
            TextView itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
            TextView itemDesc = (TextView) convertView.findViewById(R.id.itemDesc);
            ImageView itemImg = (ImageView) convertView.findViewById(R.id.itemImg);

            itemTitle.setText(data.strTitle);
            itemDesc.setText(data.strDesc);
            itemImg.setImageResource(data.imgId);


            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i =0; i< 30; i++){
            list.add(new MyData(R.mipmap.ic_launcher,"title:" + i , "desc:" + i));
        }

        ListView listView = (ListView)findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }
}
