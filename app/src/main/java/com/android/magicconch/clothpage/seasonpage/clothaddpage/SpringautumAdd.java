package com.android.magicconch.clothpage.seasonpage.clothaddpage;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;

public class SpringautumAdd extends AppCompatActivity {

    EditText clothNameEditText;
    CheckBox thinCheckBox, mediumCheckBox, thickCheckBox;
    EditText descriptionEditText;
    Button completeButton;

    class SaveDataTask extends AsyncTask<Void, Void, Void> {
        String clothName;
        boolean isThin, isMedium, isThick;
        String description;

        public SaveDataTask(String clothName, boolean isThin, boolean isMedium, boolean isThick, String description) {
            this.clothName = clothName;
            this.isThin = isThin;
            this.isMedium = isMedium;
            this.isThick = isThick;
            this.description = description;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences sharedPreferences = getSharedPreferences("Clothes", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("clothName", clothName);
            editor.putBoolean("isThin", isThin);
            editor.putBoolean("isMedium", isMedium);
            editor.putBoolean("isThick", isThick);
            editor.putString("description", description);

            editor.apply();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(SpringautumAdd.this, "작성 완료", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.springautm_add);
        setTitle("봄 옷 추가페이지");

        clothNameEditText = findViewById(R.id.cloth_name);
        thinCheckBox = findViewById(R.id.thin_check);
        mediumCheckBox = findViewById(R.id.medium_check);
        thickCheckBox = findViewById(R.id.thick_check);
        descriptionEditText = findViewById(R.id.description);
        completeButton = findViewById(R.id.complete_button);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clothName = clothNameEditText.getText().toString();
                boolean isThin = thinCheckBox.isChecked();
                boolean isMedium = mediumCheckBox.isChecked();
                boolean isThick = thickCheckBox.isChecked();
                String description = descriptionEditText.getText().toString();

                new SaveDataTask(clothName, isThin, isMedium, isThick, description).execute();
            }
        });
    }
}
