package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.magicconch.clothpage.MainCloth;

public class MainPage extends AppCompatActivity {

    Button ClothBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        setTitle("메인 페이지");

        // 옷장 페이지 이동 부분
        ClothBtn = (Button) findViewById(R.id.clothBtn);
        ClothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainCloth.class);
                startActivity(intent);
            }
        });

    }
}