package com.android.magicconch.clothpage.seasonpage.clothaddpage.closet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.SpringautumAdd;

public class closet1 extends AppCompatActivity {
    TextView clothNameTextView, clothNameTopTextView, clothNameBottomTextView, clothNameShoesTextView, clothNameAccessoryTextView;
    ImageView clothPictureImageView, clothPictureTopImageView, clothPictureBottomImageView, clothPictureShoesImageView, clothPictureAccessoryImageView;
    Button moreButton, replaceButton;
    TextView descriptionTextView;
    String clothName;
    int clothPictureResId;
    String description;
    String clothType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closet1);

        clothNameTextView = findViewById(R.id.cloth_name_outer);
        clothPictureImageView = findViewById(R.id.cloth_picture_outer);
        clothNameTopTextView = findViewById(R.id.cloth_name_top);
        clothPictureTopImageView = findViewById(R.id.cloth_picture_top);
        clothNameBottomTextView = findViewById(R.id.cloth_name_bottom);
        clothPictureBottomImageView = findViewById(R.id.cloth_picture_bottom);
        clothNameShoesTextView = findViewById(R.id.cloth_name_shoes);
        clothPictureShoesImageView = findViewById(R.id.cloth_picture_shoes);
        clothNameAccessoryTextView = findViewById(R.id.cloth_name_accessory);
        clothPictureAccessoryImageView = findViewById(R.id.cloth_picture_accessory);
        moreButton = findViewById(R.id.more_button);
        replaceButton = findViewById(R.id.replace_button);
        descriptionTextView = findViewById(R.id.description);

        // SharedPreferences에서 데이터 불러오기
        String[] clothTypes = {"아우터", "상의", "하의", "신발", "악세사리"};

        SharedPreferences sharedPreferences = getSharedPreferences("Closet1", MODE_PRIVATE);
        for (String clothType : clothTypes) {
            String clothNameKey = "clothName" + clothType;
            String clothName = sharedPreferences.getString(clothNameKey, "");

            String isThinKey = "isThin" + clothType;
            boolean isThin = sharedPreferences.getBoolean(isThinKey, false);

            String isMediumKey = "isMedium" + clothType;
            boolean isMedium = sharedPreferences.getBoolean(isMediumKey, false);

            String isThickKey = "isThick" + clothType;
            boolean isThick = sharedPreferences.getBoolean(isThickKey, false);

            String descriptionKey = "description" + clothType;
            String description = sharedPreferences.getString(descriptionKey, "");

            String selectedImageResIdKey = "selectedImageResId" + clothType;
            int clothPictureResId = sharedPreferences.getInt(selectedImageResIdKey, 0);

            switch (clothType) {
                case "아우터":
                    clothNameTextView = findViewById(R.id.cloth_name_outer);
                    clothPictureImageView = findViewById(R.id.cloth_picture_outer);
                    break;
                case "상의":
                    clothNameTextView = findViewById(R.id.cloth_name_top);
                    clothPictureImageView = findViewById(R.id.cloth_picture_top);
                    break;
                case "하의":
                    clothNameTextView = findViewById(R.id.cloth_name_bottom);
                    clothPictureImageView = findViewById(R.id.cloth_picture_bottom);
                    break;
                case "신발":
                    clothNameTextView = findViewById(R.id.cloth_name_shoes);
                    clothPictureImageView = findViewById(R.id.cloth_picture_shoes);
                    break;
                case "악세사리":
                    clothNameTextView = findViewById(R.id.cloth_name_accessory);
                    clothPictureImageView = findViewById(R.id.cloth_picture_accessory);
                    break;
            }

            if (clothNameTextView != null && clothPictureImageView != null) {
                clothNameTextView.setText(clothName);
                clothPictureImageView.setImageResource(clothPictureResId);
            }

            clothNameTextView.setText(clothName);
            clothPictureImageView.setImageResource(clothPictureResId);
            descriptionTextView.setText(description);
            descriptionTextView.setVisibility(View.GONE);

            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    descriptionTextView.setVisibility(View.VISIBLE);
                }
            });

            replaceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(closet1.this, SpringautumAdd.class);
                    startActivity(intent);
                }
            });
        }
    }
}