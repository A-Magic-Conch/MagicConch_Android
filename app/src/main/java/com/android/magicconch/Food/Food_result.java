package com.android.magicconch.Food;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.magicconch.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Food_result extends AppCompatActivity {
    private TextView TV1;
    private TextView TV2;
    private TextView TV3;
    private TextView TV4;
    private TextView TV5;
    private TextView Food_name;
    private Button btn;
    private ProgressBar progressBar;
    private int limit;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_result);

        Intent intent = getIntent();
        List<String> SV = (List<String>) intent.getStringArrayListExtra("seekBarValues");

        progressBar = findViewById(R.id.pb1);

        TV1 = findViewById(R.id.TV1);
        TV2 = findViewById(R.id.TV2);
        TV3 = findViewById(R.id.TV3);
        TV4 = findViewById(R.id.TV4);
        TV5 = findViewById(R.id.TV5);
        Food_name = findViewById(R.id.food_name);
        btn = findViewById(R.id.next);

        btn.setText(SV.get(3)+SV.get(4)+SV.get(5)+SV.get(6));

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        progress = 0;
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
                    TV5.setText(result.toString());

                    String[][] twoDimArray;
                    try {
                        // 문자열 처리를 통해 '['와 ']'를 제거하고 ','로 split
                        String result_s=result.toString();
                        String[] rows = result_s.substring(6, result_s.length() - 2).split("\\], \\[");

                        // 2차원 배열로 변환
                        twoDimArray = new String[rows.length][];
                        for (int i = 0; i < rows.length; i++) {
                            String[] elements = rows[i].split(", ");
                            twoDimArray[i] = new String[elements.length];

                            for (int j = 0; j < elements.length; j++) {
                                // 각 요소에서 따옴표를 제거
                                twoDimArray[i][j] = elements[j].replace("'", "");
                            }
                        }
                    } catch (Exception e) {
                        // 예외 처리: 파싱 실패 시
                        twoDimArray = new String[][]{{String.valueOf(e), "", "", "", "", "", "0.0"}};
                        e.printStackTrace();
                    }

                    if (twoDimArray.length!=1){
                        TV1.setText("2: "+twoDimArray[1][0]+twoDimArray[1][6]);
                        TV2.setText("3: "+twoDimArray[2][0]+twoDimArray[2][6]);
                        TV3.setText("4: "+twoDimArray[3][0]+twoDimArray[3][6]);
                        TV4.setText("5: "+twoDimArray[4][0]+twoDimArray[4][6]);
                    }

                    limit = (int)(Float.parseFloat(twoDimArray[0][6])*100);
                    if (limit >= 100)
                        limit = 99;

                    Food_name.setText(twoDimArray[0][0]+String.valueOf(limit));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (progress < limit) {
                                progress += 1;
                                progressBar.setProgress(progress);
                                // 시간 지연
                                try {
                                    if (progress == 1)
                                        Thread.sleep(500);
                                    Thread.sleep((long)((120/(2+limit-progress))+1));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
            }
            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                String stackTrace = Log.getStackTraceString(t);
                TV1.setText(stackTrace);
            }
        }
                );
//            }
//        });
    }
}