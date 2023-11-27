package com.android.magicconch.clothpage.seasonpage;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.SpringautumAdd;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.springcloset.spcloset1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.springcloset.spcloset2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.springcloset.spcloset3;
import com.bumptech.glide.Glide;

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
                Intent intent = new Intent(getApplicationContext(), spcloset1.class);
                startActivity(intent);
            }
        });

        closet2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), spcloset2.class);
                startActivity(intent);
            }
        });

        closet3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), spcloset3.class);
                startActivity(intent);
            }
        });

        rekpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로딩창 생성
                Dialog loadingDialog = new Dialog(SpringautumCloth.this);
                loadingDialog.setContentView(R.layout.ladaing_dialog);
                loadingDialog.setCancelable(false);
                ImageView loadingImage = loadingDialog.findViewById(R.id.loading_image);
                Glide.with(SpringautumCloth.this).load(R.drawable.pow).into(loadingImage);

                // Dialog의 위치와 크기 설정
                Window window = loadingDialog.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                    params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    params.gravity = Gravity.CENTER;
                    window.setAttributes(params);
                }

                loadingDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingDialog.isShowing()) {
                            loadingDialog.dismiss();
                        }
                        Intent intent = new Intent(SpringautumCloth.this, RekAddPage.class);
                        startActivity(intent);
                    }
                }, 4500);
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
