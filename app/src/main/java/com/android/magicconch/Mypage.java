package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Mypage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        // 사용자 정보를 SharedPreferences에서 불러와서 표시
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String userEmail = preferences.getString("email", ""); // "email" 키를 사용하여 이메일을 불러옴

// 사용자 정보를 화면에 표시
        TextView emailTextView = findViewById(R.id.name1);
        emailTextView.setText(userEmail);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, MainPage.class); // Login은 이동하려는 액티비티의 클래스 이름
                startActivity(intent);
            }
        });

    }
}

