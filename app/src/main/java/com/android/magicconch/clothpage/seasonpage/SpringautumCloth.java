package com.android.magicconch.clothpage.seasonpage;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.SpringautumAdd;

public class SpringautumCloth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.springautumn_cloth);
        setTitle("봄/옷장페이지");

        Button rekpageBtn = findViewById(R.id.goToRekAddPage);
        Button SpAddBtn = findViewById(R.id.SpAddPage);

        rekpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RekAddPage.class);
                startActivity(intent);
            }
        });

        SpAddBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpringautumAdd.class);
                startActivity(intent);
            }
        });



    }
}
