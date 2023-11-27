package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.magicconch.Food.Food_result;
import com.android.magicconch.activity.Activity_result;
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
        dateTextView.setText("📆 " + date);


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

        SharedPreferences sharedPreferences = getSharedPreferences("SelectedText", MODE_PRIVATE);
        String text = sharedPreferences.getString("text", "");

        Button button = new Button(this);
        button.setId(View.generateViewId());
        button.setText(text);
        button.setBackgroundColor(Color.parseColor("#AAE83F")); // 버튼 배경 색상 설정
        button.setTextColor(Color.WHITE);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); // 버튼 텍스트 크기 설정
        button.setTypeface(null, Typeface.BOLD);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height); // 버튼 크기 설정
        params.gravity = Gravity.CENTER; // 버튼을 중앙에 배치
        params.setMargins(40, 10, 40, 10); // 버튼 마진 설정. 왼쪽과 오른쪽 마진을 20dp로 설정
        button.setLayoutParams(params);

        LinearLayout layout = findViewById(R.id.option);
        layout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, Activity_result.class);
                intent.putExtra("plz", text != null ? text : "명소");
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences2 = getSharedPreferences("FoodPreferences", MODE_PRIVATE);
        String foodPrefs = sharedPreferences2.getString("food_prefs", "");

        Button buttonFood = new Button(this);
        buttonFood.setId(View.generateViewId());
        buttonFood.setText("Food"); // 버튼 텍스트를 'Food'로 설정
        buttonFood.setBackgroundColor(Color.parseColor("#AAE83F")); // 버튼 배경 색상 설정
        buttonFood.setTextColor(Color.WHITE);
        buttonFood.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); // 버튼 텍스트 크기 설정
        buttonFood.setTypeface(null, Typeface.BOLD);

        int height2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height2); // 버튼 크기 설정
        params2.gravity = Gravity.CENTER; // 버튼을 중앙에 배치
        params2.setMargins(40, 10, 40, 10); // 버튼 마진 설정. 왼쪽과 오른쪽 마진을 20dp로 설정
        buttonFood.setLayoutParams(params2);

        LinearLayout layout2 = findViewById(R.id.option2);
        layout2.addView(buttonFood);

        buttonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, Food_result.class);
                startActivity(intent);
            }
        });

    }
}
