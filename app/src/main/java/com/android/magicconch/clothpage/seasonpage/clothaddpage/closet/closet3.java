package com.android.magicconch.clothpage.seasonpage.clothaddpage.closet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.SpringautumAdd;

public class closet3 extends AppCompatActivity {
    TextView clothNameTextView;
    ImageView clothPictureImageView;
    Button moreButton, replaceButton;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closet3);

        clothNameTextView = findViewById(R.id.cloth_name);
        clothPictureImageView = findViewById(R.id.cloth_picture);
        moreButton = findViewById(R.id.more_button);
        replaceButton = findViewById(R.id.replace_button);
        descriptionTextView = findViewById(R.id.description);

        Intent intent = getIntent();
        String clothName = intent.getStringExtra("clothName");
        int clothPictureResId = intent.getIntExtra("selectedImageResId", 0);
        String description = intent.getStringExtra("description");

        clothNameTextView.setText(clothName);
        clothPictureImageView.setImageResource(clothPictureResId);
        descriptionTextView.setText(description);
        descriptionTextView.setVisibility(View.GONE);    // 설명은 기본적으로 숨겨져 있습니다.

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionTextView.setVisibility(View.VISIBLE);
            }
        });

        replaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(closet3.this, SpringautumAdd.class);
                startActivity(intent);
            }
        });
    }
}
