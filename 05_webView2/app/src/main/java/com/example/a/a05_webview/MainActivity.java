package com.example.a.a05_webview;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    ProgressDialog dlg;

    class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //Toast.makeText(MainActivity.this, "onPageStarted", Toast.LENGTH_SHORT).show();
            dlg.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //Toast.makeText(MainActivity.this, "onPageStarted", Toast.LENGTH_SHORT).show();
            dlg.dismiss();
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Toast.makeText(MainActivity.this, Uri.parse(url).getHost() , Toast.LENGTH_SHORT).show();
            if(Uri.parse(url).getHost().equals("m.news.naver.com")){
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dlg = new ProgressDialog(MainActivity.this);

        webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.daum.net");
    }

    public void onBtnOkClick(View v){
        EditText editURL = (EditText)findViewById(R.id.editURL);
        String url = editURL.getText().toString();
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
