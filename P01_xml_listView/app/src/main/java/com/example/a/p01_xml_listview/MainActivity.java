package com.example.a.p01_xml_listview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<WeatherInfo> list = new ArrayList<WeatherInfo>();
    WeatherAdapter adapter;

    class WeatherInfo{
        int iconId;
        String forecast;
    }

    class WeatherAdapter extends BaseAdapter {

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
                convertView = getLayoutInflater().inflate(R.layout.item_view, null);
            }
            WeatherInfo data = list.get(position);
            TextView itemDesc = (TextView) convertView.findViewById(R.id.itemDesc);
            ImageView itemImg = (ImageView) convertView.findViewById(R.id.itemImg);
            itemImg.setImageResource(data.iconId);
            itemDesc.setText(data.forecast);
            return convertView;
        }
    }

    class MyDomParser extends AsyncTask<String , Void, Document>{
        private String getElementText(Element data,String tag){
            NodeList hourList = data.getElementsByTagName(tag);
            Element hour = (Element) hourList.item(0);
            NodeList textNodeList = hour.getChildNodes();

            return textNodeList.item(0).getNodeValue();
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            String str = "";
            NodeList dataList = document.getElementsByTagName("data");

            for(int i=0; i< dataList.getLength(); i++){
                Element data = (Element) dataList.item(i);
                String strHour = getElementText(data, "hour");
                String strDay = getElementText(data, "day");
                String strTemp = getElementText(data, "temp");
                String strWfKor = getElementText(data, "wfKor");

                WeatherInfo info = new WeatherInfo();
                if(strWfKor.indexOf("비") > -1){
                    info.iconId = R.drawable.rain;
                }else if(strWfKor.indexOf("구름") > -1 || strWfKor.indexOf("흐림") > -1){
                    info.iconId = R.drawable.cloud;
                }else if(strWfKor.indexOf("맑음") > -1){
                    info.iconId = R.drawable.sunny;
                }
                info.forecast = strDay + " day" + strHour + " hour" + strWfKor + " wfKor" + strTemp + " temp";
                list.add(info);
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        protected Document doInBackground(String... params) {
            Document doc = null;
            try {
                URL url = new URL(params[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = dbf.newDocumentBuilder();
                doc = builder.parse(url.openStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return doc;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDomParser task = new MyDomParser();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154052000");
        listView = (ListView)findViewById(R.id.listView);
        adapter = new WeatherAdapter();
        listView.setAdapter(adapter);

    }
}
