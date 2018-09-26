package com.boyma.habrrsstitles.ui.NewsActivity;

import android.annotation.TargetApi;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.boyma.habrrsstitles.R;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String link = getIntent().getStringExtra("link");
        System.out.println("link:"+link);

        WebView web = findViewById(R.id.web);
        web.loadUrl(link);

        setContentView(web);
    }
}
