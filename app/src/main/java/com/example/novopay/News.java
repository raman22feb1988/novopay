package com.example.novopay;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
// import android.view.View;
import android.webkit.WebView;
// import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class News extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        Intent intent3 = getIntent();
        String url = intent3.getStringExtra("url");

        WebView w1 = findViewById(R.id.webview1);
        w1.loadUrl(url);

//        Button b1 = findViewById(R.id.button1);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent4 = new Intent(News.this, Home.class);
//                startActivity(intent4);
//                finish();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(News.this, Home.class);
        startActivity(intent2);
        finish();
    }
}