package com.android.magicconch.clothpage.seasonpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.SummerAdd;

public class SummerCloth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summer_cloth);
        setTitle("여름옷장페이지");


        Button rekpageBtn = findViewById(R.id.goToRekAddPage);
        Button SuAddBtn = findViewById(R.id.SumAddPage);

        rekpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RekAddPage.class);
                startActivity(intent);
            }
        });

        SuAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SummerAdd.class);
                startActivity(intent);
            }
        });




    }
}
