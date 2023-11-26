package com.android.magicconch.clothpage.seasonpage.clothaddpage.springcloset;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;

public class spcloset2 extends AppCompatActivity {

    ImageView clothPictureImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spcloset1);

        // SharedPreferences에서 데이터 불러오기
        String[] clothTypes = {"아우터", "상의", "하의", "신발", "악세사리"};
        SharedPreferences sharedPreferences = getSharedPreferences("Closet2", MODE_PRIVATE);
        for (String clothType : clothTypes) {
            String selectedImageResIdKey = "selectedImageResId" + clothType;
            int clothPictureResId = sharedPreferences.getInt(selectedImageResIdKey, 0);

            switch (clothType) {
                case "아우터":
                    clothPictureImageView = findViewById(R.id.cloth_picture_outer);
                    break;
                case "상의":
                    clothPictureImageView = findViewById(R.id.cloth_picture_top);
                    break;
                case "하의":
                    clothPictureImageView = findViewById(R.id.cloth_picture_bottom);
                    break;
                case "신발":
                    clothPictureImageView = findViewById(R.id.cloth_picture_shoes);
                    break;
                case "악세사리":
                    clothPictureImageView = findViewById(R.id.cloth_picture_accessory);
                    break;
            }

            if (clothPictureImageView != null) {
                try {
                    clothPictureImageView.setImageResource(clothPictureResId);
                } catch (Resources.NotFoundException e) {
                    Log.e("Closet1", "Invalid image resource ID: " + clothPictureResId, e);
                }
            }
        }
    }
}