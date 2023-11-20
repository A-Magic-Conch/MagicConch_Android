package com.android.magicconch.Food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.android.magicconch.R;

import java.util.ArrayList;
import java.util.List;

public class Food extends AppCompatActivity {
    private CheckBox CB_sp;
    private CheckBox CB_su;
    private CheckBox CB_sa;
    private CheckBox CB_w;
    private SeekBar SB_sp;
    private SeekBar SB_su;
    private SeekBar SB_sa;
    private SeekBar SB_w;

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
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> seekBarValues = new ArrayList<>();
                seekBarValues.add("0");
                seekBarValues.add("0");
                seekBarValues.add("0");
                seekBarValues.add(SB_sp.isEnabled() ? String.valueOf(SB_sp.getProgress()*.1f) : "0");
                seekBarValues.add(SB_su.isEnabled() ? String.valueOf(SB_su.getProgress()*.1f) : "0");
                seekBarValues.add(SB_sa.isEnabled() ? String.valueOf(SB_sa.getProgress()*.1f) : "0");
                seekBarValues.add(SB_w.isEnabled() ? String.valueOf(SB_w.getProgress()*.1f) : "0");

                Intent intent = new Intent(Food.this, Food_result.class);
                intent.putStringArrayListExtra("seekBarValues", seekBarValues);
                startActivity(intent);
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