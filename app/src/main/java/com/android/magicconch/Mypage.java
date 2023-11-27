package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset3;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mypage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        TextView dateTextView = findViewById(R.id.date_input);
        dateTextView.setText("날짜: " + date);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
            TextView emailTextView = findViewById(R.id.name1);
            emailTextView.setText(userEmail);
        } else {

            startActivity(new Intent(this, Login.class));
            finish();
        }

        findViewById(R.id.wintercloset1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Mypage.this, wincloset1.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.wintercloset2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Mypage.this, wincloset2.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.wintercloset3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Mypage.this, wincloset3.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Mypage.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, MainPage.class);
                startActivity(intent);
            }
        });

    }
}
