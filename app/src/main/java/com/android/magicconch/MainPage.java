package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

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