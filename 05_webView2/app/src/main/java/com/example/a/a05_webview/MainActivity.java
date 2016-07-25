package com.example.a.a05_webview;

import android.graphics.Bitmap;
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

    class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Toast.makeText(MainActivity.this, "onPageStarted", Toast.LENGTH_SHORT).show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Toast.makeText(MainActivity.this, "onPageStarted", Toast.LENGTH_SHORT).show();
            super.onPageFinished(view, url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.naver.com");
    }

    public void onBtnOkClick(View v){
        EditText editURL = (EditText)findViewById(R.id.editURL);
        String url = editURL.getText().toString();
        webView.loadUrl(url);
    }
}
