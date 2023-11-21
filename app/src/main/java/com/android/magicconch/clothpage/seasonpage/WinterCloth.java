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
import com.android.magicconch.clothpage.seasonpage.clothaddpage.WinterAdd;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.summercloset.sucloset1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.summercloset.sucloset2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.summercloset.sucloset3;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset3;
import com.bumptech.glide.Glide;

public class WinterCloth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winter_cloth);
        setTitle("겨울옷장페이지");

        Button rekpageBtn = findViewById(R.id.goToRekAddPage);
        Button WiAddBtn = findViewById(R.id.WinAddPage);

        Button closet1Btn = findViewById(R.id.wincloset1_button);
        Button closet2Btn = findViewById(R.id.wincloset2_button);
        Button closet3Btn = findViewById(R.id.wincloset3_button);


        closet1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), wincloset1.class);
                startActivity(intent);
            }
        });

        closet2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), wincloset2.class);
                startActivity(intent);
            }
        });

        closet3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), wincloset3.class);
                startActivity(intent);
            }
        });

        rekpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로딩창 생성
                Dialog loadingDialog = new Dialog(WinterCloth.this);
                loadingDialog.setContentView(R.layout.ladaing_dialog);
                loadingDialog.setCancelable(false);
                ImageView loadingImage = loadingDialog.findViewById(R.id.loading_image);
                Glide.with(WinterCloth.this).load(R.drawable.pow).into(loadingImage);

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
                        Intent intent = new Intent(WinterCloth.this, RekAddPage.class);
                        startActivity(intent);
                    }
                }, 4500);
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
