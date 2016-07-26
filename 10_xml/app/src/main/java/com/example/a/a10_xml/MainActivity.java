package com.example.a.a10_xml;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
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

                str += strDay + " day" + strHour + " hour" + strWfKor + " wfKor" + strTemp + " temp\n";
            }

            textView.setText(str);
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

    class MyPullParser extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }

        @Override
        protected String doInBackground(String... params) {
            String str = "";
            try{
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new URL(params[0]).openStream(), "utf-8");
                int eventType = xpp.getEventType();
                boolean bRead = false;
                while (eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG :
                            if(xpp.getName().equals("hour") || xpp.getName().equals("day")
                               || xpp.getName().equals("temp") || xpp.getName().equals("wfKor")){
                                bRead = true;
                            }
                            break;
                        case XmlPullParser.END_TAG :
                            break;
                        case XmlPullParser.TEXT:
                            if(bRead == true){
                                xpp.getText();
                                str += " " + xpp.getText();
                                bRead = false;
                            }
                            break;
                    }
                    eventType = xpp.next();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return str;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        //MyDomParser task = new MyDomParser();
        MyPullParser task = new MyPullParser();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154052000");
    }
}
