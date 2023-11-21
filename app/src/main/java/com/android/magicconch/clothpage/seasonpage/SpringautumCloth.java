package com.android.magicconch.clothpage.seasonpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.SpringautumAdd;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.closet.closet1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.closet.closet2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.closet.closet3;

public class SpringautumCloth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.springautumn_cloth);
        setTitle("봄/옷장페이지");

        Button closet1Btn = findViewById(R.id.closet1_button);
        Button closet2Btn = findViewById(R.id.closet2_button);
        Button closet3Btn = findViewById(R.id.closet3_button);
        Button rekpageBtn = findViewById(R.id.goToRekAddPage);
        Button SpAddBtn = findViewById(R.id.SpAddPage);

        closet1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), closet1.class);
                startActivity(intent);
            }
        });

        closet2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), closet2.class);
                startActivity(intent);
            }
        });

        closet3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), closet3.class);
                startActivity(intent);
            }
        });

        rekpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RekAddPage.class);
                startActivity(intent);
            }
        });

        SpAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpringautumAdd.class);
                startActivity(intent);
            }
        });

    }
}
