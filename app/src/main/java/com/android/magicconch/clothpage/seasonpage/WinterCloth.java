package com.android.magicconch.clothpage.seasonpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.WinterAdd;

public class WinterCloth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winter_cloth);
        setTitle("겨울옷장페이지");

        Button rekpageBtn = findViewById(R.id.goToRekAddPage);
        Button WiAddBtn = findViewById(R.id.WinAddPage);

        rekpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RekAddPage.class);
                startActivity(intent);
            }
        });

        WiAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WinterAdd.class);
                startActivity(intent);
            }
        });

    }
}
