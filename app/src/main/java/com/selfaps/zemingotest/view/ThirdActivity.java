package com.selfaps.zemingotest.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.selfaps.zemingotest.R;
import com.selfaps.zemingotest.utils.Constants;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String urlString = intent.getStringExtra(Constants.EXTRAS_LINK);

        WebView wvHtml = (WebView) findViewById(R.id.web_view);
        wvHtml.getSettings().setBuiltInZoomControls(true);
        wvHtml.getSettings().setJavaScriptEnabled(true);
        wvHtml.loadUrl(urlString);

        Toast.makeText(this,"Load link: "+urlString,Toast.LENGTH_SHORT).show();
    }
}
