package com.android.magicconch.clothpage.seasonpage.clothaddpage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.closet.closet1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.closet.closet2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.closet.closet3;

import java.io.InputStream;

public class SpringautumAdd extends AppCompatActivity {
    String[] imageNames = {
            "neat1", "neat2", "neat3", "neat4",
            "pants1", "pants2", "pants3", "pants4",
            "slacks1", "slacks2", "slacks3", "slacks4",
            "mantoman1", "mantoman2", "mantoman3", "mantoman4",
            "hood1", "hood2", "hood3", "hood4",
            "coat1", "coat2"
    };
    EditText clothNameEditText;
    CheckBox thinCheckBox, mediumCheckBox, thickCheckBox;
    EditText descriptionEditText;
    Button completeButton, pictureSelectButton;
    ImageView clothPictureImageView;
    Spinner clothTypeSpinner;
    String clothType;
    int selectedImageResId;

    public static final int PICK_IMAGE = 1;

    class SaveDataTask extends AsyncTask<Void, Void, Void> {
        String clothName;
        boolean isThin, isMedium, isThick;
        String description;
        String clothType;

        public SaveDataTask(ClothInfo clothInfo) {
            this.clothName = clothInfo.clothName;
            this.isThin = clothInfo.isThin;
            this.isMedium = clothInfo.isMedium;
            this.isThick = clothInfo.isThick;
            this.description = clothInfo.description;
            this.clothType = clothInfo.clothType;
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
            editor.putString("clothType", clothType);

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
        clothTypeSpinner = findViewById(R.id.cloth_type_spinner);
        pictureSelectButton = findViewById(R.id.picture_select_button);
        clothPictureImageView = findViewById(R.id.cloth_picture);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cloth_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clothTypeSpinner.setAdapter(adapter);
        clothTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clothType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                clothType = "";
            }
        });

        pictureSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SpringautumAdd.this);
                builder.setTitle("Select a picture")
                        .setItems(imageNames, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedImageName = imageNames[which];
                                selectedImageResId = getResources().getIdentifier(selectedImageName, "drawable", getPackageName());
                                clothPictureImageView.setImageResource(selectedImageResId);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String clothName = clothNameEditText.getText().toString();
                final boolean isThin = thinCheckBox.isChecked();
                final boolean isMedium = mediumCheckBox.isChecked();
                final boolean isThick = thickCheckBox.isChecked();
                final String description = descriptionEditText.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(SpringautumAdd.this);
                builder.setTitle("Select a closet")
                        .setItems(new String[]{"옷장 1", "옷장 2", "옷장 3"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedPreferences;
                                String closet = null;
                                switch (which) {
                                    case 0:
                                        closet = "Closet1";
                                        break;
                                    case 1:
                                        closet = "Closet2";
                                        break;
                                    case 2:
                                        closet = "Closet3";
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Invalid closet number");
                                }

                                sharedPreferences = getSharedPreferences(closet, MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                String clothKey = "clothName" + clothType;
                                editor.putString(clothKey, clothName);

                                String isThinKey = "isThin" + clothType;
                                editor.putBoolean(isThinKey, isThin);

                                String isMediumKey = "isMedium" + clothType;
                                editor.putBoolean(isMediumKey, isMedium);

                                String isThickKey = "isThick" + clothType;
                                editor.putBoolean(isThickKey, isThick);

                                String descriptionKey = "description" + clothType;
                                editor.putString(descriptionKey, description);

                                String selectedImageResIdKey = "selectedImageResId" + clothType;
                                editor.putInt(selectedImageResIdKey, selectedImageResId);

                                editor.apply();
                                editor.apply();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if (data == null) {
                return;
            }

            try {
                Uri selectedImageUri = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                clothPictureImageView.setImageBitmap(selectedImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}