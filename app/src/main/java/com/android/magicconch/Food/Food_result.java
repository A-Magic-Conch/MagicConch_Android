package com.android.magicconch.Food;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.magicconch.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Food_result extends AppCompatActivity {
    private String result_s;
    private TextView TV1, TV2, TV3, TV4;
    private CardView CV1, CV2, CV3, CV4;
    private ProgressBar PB1, PB2, PB3, PB4;
    private TextView Food_name;
    private ImageView Food_img;
    private Button btn;
    private ProgressBar progressBar;
    private int limit, progress;
    private List<TextView> textViewList = new ArrayList<>();
    private List<CardView> cardViewList = new ArrayList<>();
    private List<ProgressBar> progressBarList = new ArrayList<>();
    private String[][] twoDimArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_result);
        Context cont = this;
        Intent intent = getIntent();
        List<String> SV = intent.getStringArrayListExtra("seekBarValues");

        progressBar = findViewById(R.id.pb1);
        CV1 = findViewById(R.id.CV1);
        textViewList.add(TV1 = findViewById(R.id.TV1));
        textViewList.add(TV2 = findViewById(R.id.TV2));
        textViewList.add(TV3 = findViewById(R.id.TV3));
        textViewList.add(TV4 = findViewById(R.id.TV4));
        cardViewList.add(CV1 = findViewById(R.id.CV1));
        cardViewList.add(CV2 = findViewById(R.id.CV2));
        cardViewList.add(CV3 = findViewById(R.id.CV3));
        cardViewList.add(CV4 = findViewById(R.id.CV4));
        progressBarList.add(PB1 = findViewById(R.id.PB1));
        progressBarList.add(PB2 = findViewById(R.id.PB2));
        progressBarList.add(PB3 = findViewById(R.id.PB3));
        progressBarList.add(PB4 = findViewById(R.id.PB4));

        Food_name = findViewById(R.id.food_name);
        Food_img = findViewById(R.id.food_img);
        btn = findViewById(R.id.next);

        if (SV != null)
            btn.setText(String.format("%s,%s,%s,%s", SV.get(3), SV.get(4), SV.get(5), SV.get(6)));


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        progress = 0;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://203.234.62.169:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService servicel = retrofit.create(RetrofitService.class);
        Call<PostResult> call = servicel.getPosts(SV != null ? SV.get(0) : "r", SV != null ? SV.get(1) : "r", SV != null ? SV.get(2) : "r",
                SV != null ? SV.get(3) : "r", SV != null ? SV.get(4) : "r", SV != null ? SV.get(5) : "r", SV != null ? SV.get(6) : "r");

        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(@NonNull Call<PostResult> call, @NonNull Response<PostResult> response) {
                if(response.isSuccessful()) {
                    PostResult result = response.body();

                    try {
                        // 문자열 처리를 통해 '['와 ']'를 제거하고 ','로 split
                        result_s=result.toString();
                        if (result_s.equals("")) {
                            twoDimArray = new String[][]{{"이건 괴식임", "r", "", "", "", "", "100.0", "none"}, {"행운의 메뉴 뽑기", "r", "r", "r", "r", "r", "101"}};
                        }
                        else {
                            String[] rows = result_s.substring(3, result_s.length() - 2).split("\\], \\[");

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
                        }
                        if (twoDimArray[0][7].equals("none"))
                            Glide.with(cont).load(R.drawable.sosad).into(Food_img);
                        else
                            Glide.with(cont).load(twoDimArray[0][7]).into(Food_img);
                    } catch (Exception e) {
                        // 예외 처리: 파싱 실패 시
                        e.printStackTrace();
                    }

                    for (int i = 1; i < twoDimArray.length; i++) {
                        cardViewList.get(i - 1).setVisibility(View.VISIBLE);
                        textViewList.get(i - 1).setText(twoDimArray[i][0]+(twoDimArray[i][6]=="101" ? "" : String.valueOf(Float.valueOf(twoDimArray[i][6])*100)));
                    }
                    limit = (int)(Float.parseFloat(twoDimArray[0][6])*100);
                    if (limit >= 100)
                        limit = 99;

                    Food_name.setText(twoDimArray[0][0]+(twoDimArray[0][1].equals("r") ? "" : String.valueOf(limit)));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (progress < limit) {
                                progress += 1;
                                progressBar.setProgress(progress);
                                for (int i = 1; i < twoDimArray.length; i++)
                                    progressBarList.get(i-1).setProgress((int)(Float.parseFloat(twoDimArray[i][6])*100*progress/limit));
                                // 시간 지연
                                try {
                                    Thread.sleep((long)((120/(2+limit-progress))+1));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                    for (int i = 1; i < twoDimArray.length; i++) {
                        int finalI = i;
                        cardViewList.get(i - 1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<String> seekBarValues = new ArrayList<>();
                                seekBarValues.add(twoDimArray[0][1].equals("r") ? "전체" : SV.get(0)); //한식중식
                                seekBarValues.add(twoDimArray[0][1].equals("r") ? "전체" : SV.get(1)); //면밥
                                seekBarValues.add(twoDimArray[0][1].equals("r") ? "전체" : SV.get(2)); //곡류육류
                                seekBarValues.add(twoDimArray[finalI][2]);
                                seekBarValues.add(twoDimArray[finalI][3]);
                                seekBarValues.add(twoDimArray[finalI][4]);
                                seekBarValues.add(twoDimArray[finalI][5]);

                                Intent intent = new Intent(Food_result.this, Food_result.class);
                                intent.putStringArrayListExtra("seekBarValues", seekBarValues);
                                finish();
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                String stackTrace = Log.getStackTraceString(t);
                TV1.setText(stackTrace);
            }
        });
    }
}