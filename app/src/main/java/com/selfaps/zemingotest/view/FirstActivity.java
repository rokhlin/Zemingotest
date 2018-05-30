package com.selfaps.zemingotest.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.selfaps.zemingotest.R;
import com.selfaps.zemingotest.utils.Utils;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRAS_LINK = "link" ;
    private TextView tvDate, tvLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvLabel = (TextView) findViewById(R.id.tv_last_visited);
        Button btnSecond= (Button) findViewById(R.id.btn_next);
        btnSecond.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvDate.setText(Utils.getDate());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String link = data.getStringExtra(EXTRAS_LINK);
        tvLabel.setText(link!= null ? link : "");
    }
}
