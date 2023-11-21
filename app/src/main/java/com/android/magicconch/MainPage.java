package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.magicconch.clothpage.MainCloth;

public class MainPage extends AppCompatActivity {

    Button ClothBtn;
    ImageButton Clothbun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        setTitle("메인 페이지");

        // 옷장 페이지 이동 부분

        Clothbun = (ImageButton) findViewById(R.id.imgBtn1);

        Clothbun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainCloth.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭 시 Login 액티비티로 이동
                Intent intent = new Intent(MainPage.this, Login.class); // Login은 이동하려는 액티비티의 클래스 이름
                startActivity(intent);
            }
        });

        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭 시 Login 액티비티로 이동
                Intent intent = new Intent(MainPage.this, Mypage.class); // Login은 이동하려는 액티비티의 클래스 이름
                startActivity(intent);
            }
        });

    }
}