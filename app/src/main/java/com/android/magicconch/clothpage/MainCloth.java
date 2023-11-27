package com.android.magicconch.clothpage;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.SpringautumCloth;
import com.android.magicconch.clothpage.seasonpage.SummerCloth;
import com.android.magicconch.clothpage.seasonpage.WinterCloth;

public class MainCloth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cloth);

        Button springatum = findViewById(R.id.springBtn);
        Button summerbtn = findViewById(R.id.summerBtn);
        Button winterbtn = findViewById(R.id.winterBtn);
        springatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SpringautumCloth.class);
                startActivity(intent);
            }
        });

        summerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SummerCloth.class);
                startActivity(intent);
            }
        });

        winterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WinterCloth.class);
                startActivity(intent);
            }
        });
    }
}

