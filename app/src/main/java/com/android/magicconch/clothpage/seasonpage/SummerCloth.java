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
import com.android.magicconch.clothpage.seasonpage.clothaddpage.SummerAdd;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.springcloset.spcloset1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.springcloset.spcloset2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.springcloset.spcloset3;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.summercloset.sucloset1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.summercloset.sucloset2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.summercloset.sucloset3;
import com.bumptech.glide.Glide;

public class SummerCloth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summer_cloth);
        setTitle("여름옷장페이지");


        Button rekpageBtn = findViewById(R.id.goToRekAddPage);
        Button SuAddBtn = findViewById(R.id.SumAddPage);
        Button closet1Btn = findViewById(R.id.sucloset1_button);
        Button closet2Btn = findViewById(R.id.sucloset2_button);
        Button closet3Btn = findViewById(R.id.sucloset3_button);


        closet1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sucloset1.class);
                startActivity(intent);
            }
        });

        closet2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sucloset2.class);
                startActivity(intent);
            }
        });

        closet3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sucloset3.class);
                startActivity(intent);
            }
        });

        rekpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로딩창 생성
                Dialog loadingDialog = new Dialog(SummerCloth.this);
                loadingDialog.setContentView(R.layout.ladaing_dialog);
                loadingDialog.setCancelable(false);
                ImageView loadingImage = loadingDialog.findViewById(R.id.loading_image);
                Glide.with(SummerCloth.this).load(R.drawable.pow).into(loadingImage);

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
                        Intent intent = new Intent(SummerCloth.this, RekAddPage.class);
                        startActivity(intent);
                    }
                }, 4500);
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
