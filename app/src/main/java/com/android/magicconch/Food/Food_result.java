package com.android.magicconch.Food;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Food_result extends AppCompatActivity {
    private TextView TV2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_result);

        Intent intent = getIntent();
        List<String> SV = (List<String>) intent.getStringArrayListExtra("seekBarValues");

        TV2 = findViewById(R.id.TV2);
        btn = findViewById(R.id.next);

        btn.setText(SV.get(3)+SV.get(4)+SV.get(5)+SV.get(6));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://203.234.62.169:5000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitService servicel = retrofit.create(RetrofitService.class);
                Call<PostResult> call = servicel.getPosts(SV.get(0),SV.get(1),SV.get(2),SV.get(3),SV.get(4),SV.get(5),SV.get(6));
                call.enqueue(new Callback<PostResult>() {
                    @Override
                    public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                        if(response.isSuccessful()) {
                            PostResult result = response.body();
                            TV2.setText(result.toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<PostResult> call, Throwable t) {
                        String stackTrace = Log.getStackTraceString(t);
                        TV2.setText(stackTrace);
                    }
                });
            }
        });
    }
}