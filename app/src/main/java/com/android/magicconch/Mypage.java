package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Mypage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            TextView name1=(TextView) findViewById(R.id.name1);
            name1.setText(name);
        } else {
            Toast.makeText(getApplicationContext(),"로그인해주세요.",Toast.LENGTH_SHORT).show();

        }


    }

}

