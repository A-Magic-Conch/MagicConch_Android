package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; // Intent 클래스를 import해야 합니다.
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainPage.class);
                startActivity(intent);
            }
        }, 5000);
    }

}
