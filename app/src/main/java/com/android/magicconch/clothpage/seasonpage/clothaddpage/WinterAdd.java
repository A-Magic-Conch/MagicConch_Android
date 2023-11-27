package com.android.magicconch.clothpage.seasonpage.clothaddpage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;

import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset1;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset2;
import com.android.magicconch.clothpage.seasonpage.clothaddpage.wintercloset.wincloset3;
import com.bumptech.glide.Glide;

import java.io.InputStream;

public class WinterAdd extends AppCompatActivity {
    String[] imageNames = {
            "neat1", "neat2", "neat3", "neat4",
            "pants1", "pants2", "pants3", "pants4",
            "slacks1", "slacks2", "slacks3", "slacks4",
            "mantoman1", "mantoman2", "mantoman3", "mantoman4",
            "hood1", "hood2", "hood3", "hood4",
            "coat1", "coat2", "shoes1", "shoes2", "shoes3",
            "aug1", "aug2", "bini1", "bini2", "bini3", "bini4",
            "cap1", "geemo1", "geemo2", "geemo3", "geemo4",
            "padding1", "padding2", "padding3", "padding4",
            "mus1", "mus2", "mo1", "mo2", "mo3", "mo4",
            "new1", "new2", "snic1"
    };

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
            Toast.makeText(WinterAdd.this, "작성 완료", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.springautm_add);
        setTitle("겨울 옷 추가페이지");

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

        pictureSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(WinterAdd.this);
                dialog.setContentView(R.layout.custom_dialog);

                GridView gridView = dialog.findViewById(R.id.custom_dialog_gridview);
                ImageAdapter adapter = new ImageAdapter(WinterAdd.this, imageNames);
                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedImageName = imageNames[position];
                        selectedImageResId = getResources().getIdentifier(selectedImageName, "drawable", getPackageName());
                        clothPictureImageView.setImageResource(selectedImageResId);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
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

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WinterAdd.this);
                builder.setTitle("옷장을 선택하세요.")
                        .setItems(new String[]{"옷장 1", "옷장 2", "옷장 3"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedPreferences;
                                String closet = null;
                                final Class closetClass;
                                switch (which) {
                                    case 0:
                                        closet = "wincloset1";
                                        closetClass = wincloset1.class;
                                        break;
                                    case 1:
                                        closet = "wincloset2";
                                        closetClass = wincloset2.class;
                                        break;
                                    case 2:
                                        closet = "wincloset3";
                                        closetClass = wincloset3.class;
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Invalid closet number");
                                }
                                sharedPreferences = getSharedPreferences(closet, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String selectedImageResIdKey = "selectedImageResId" + clothType;
                                editor.putInt(selectedImageResIdKey, selectedImageResId);
                                editor.apply();

                                Dialog loadingDialog = new Dialog(WinterAdd.this);
                                loadingDialog.setContentView(R.layout.loading_dialog);
                                loadingDialog.setCancelable(false);
                                ImageView loadingImage = loadingDialog.findViewById(R.id.loading_image);
                                Glide.with(WinterAdd.this).load(R.drawable.check).into(loadingImage);


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
                                        Intent intent = new Intent(WinterAdd.this, closetClass);
                                        startActivity(intent);
                                    }
                                }, 3000);
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