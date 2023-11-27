package com.android.magicconch.Food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Food extends AppCompatActivity {
    private CheckBox CB_sp;
    private CheckBox CB_su;
    private CheckBox CB_sa;
    private CheckBox CB_w;
    private SeekBar SB_sp;
    private SeekBar SB_su;
    private SeekBar SB_sa;
    private SeekBar SB_w;

    private TextInputLayout textInputLayout1;
    private AutoCompleteTextView autoCompleteTextView1;
    private TextInputLayout textInputLayout2;
    private AutoCompleteTextView autoCompleteTextView2;
    private TextInputLayout textInputLayout3;
    private AutoCompleteTextView autoCompleteTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        CB_sp = findViewById(R.id.CB_sp);
        CB_su = findViewById(R.id.CB_su);
        CB_sa = findViewById(R.id.CB_sa);
        CB_w = findViewById(R.id.CB_w);
        SB_sp = findViewById(R.id.SB_sp);
        SB_su = findViewById(R.id.SB_su);
        SB_sa = findViewById(R.id.SB_sa);
        SB_w = findViewById(R.id.SB_w);

        setupCheckBox(CB_sp, SB_sp);
        setupCheckBox(CB_su, SB_su);
        setupCheckBox(CB_sa, SB_sa);
        setupCheckBox(CB_w, SB_w);

        textInputLayout1 = findViewById(R.id.inputLayout1);
        textInputLayout2 = findViewById(R.id.inputLayout2);
        textInputLayout3 = findViewById(R.id.inputLayout3);
        autoCompleteTextView1 = findViewById(R.id.text_item1);
        autoCompleteTextView2 = findViewById(R.id.text_item2);
        autoCompleteTextView3 = findViewById(R.id.text_item3);

        String[] items1 = {"전체", "한식", "중식", "일식", "양식", "기타"};
        String[] items2 = {"전체", "밥", "면", "국물", "찜", "구이", "튀김", "볶음", "제과", "기타"};
        String[] items3 = {"전체", "곡류", "육류", "채소류", "어패류"};
        ArrayAdapter<String> itemAdapter1 = new ArrayAdapter<>(this,
                R.layout.item_list, items1);
        autoCompleteTextView1.setAdapter(itemAdapter1);
        ArrayAdapter<String> itemAdapter2 = new ArrayAdapter<>(this,
                R.layout.item_list, items2);
        autoCompleteTextView2.setAdapter(itemAdapter2);
        ArrayAdapter<String> itemAdapter3 = new ArrayAdapter<>(this,
                R.layout.item_list, items3);
        autoCompleteTextView3.setAdapter(itemAdapter3);

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> seekBarValues = new ArrayList<>();
                seekBarValues.add(autoCompleteTextView1.getText().toString()); //한식중식
                seekBarValues.add(autoCompleteTextView2.getText().toString()); //면밥
                seekBarValues.add(autoCompleteTextView3.getText().toString()); //곡류육류
                seekBarValues.add(SB_w.isEnabled() ? String.valueOf(SB_w.getProgress()*.1f) : "r");
                seekBarValues.add(SB_su.isEnabled() ? String.valueOf(SB_su.getProgress()*.1f) : "r");
                seekBarValues.add(SB_sa.isEnabled() ? String.valueOf(SB_sa.getProgress()*.1f) : "r");
                seekBarValues.add(SB_sp.isEnabled() ? String.valueOf(SB_sp.getProgress()*.1f) : "r");


                Intent intent = new Intent(Food.this, Food_result.class);
                intent.putStringArrayListExtra("seekBarValues", seekBarValues);
                startActivity(intent);

                SharedPreferences sharedPreferences = getSharedPreferences("FoodPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.apply();


            }
        });
    }

    private void setupCheckBox(CheckBox checkBox, SeekBar seekBar) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                seekBar.setEnabled(isChecked);
            }

        });
    }
}