package com.android.magicconch.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.magicconch.R;
import com.android.magicconch.request.PostResult;
import com.android.magicconch.request.RetrofitService;
import com.nex3z.flowlayout.FlowLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity extends AppCompatActivity {
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    private Button nextButton;
    private String selectedText1;
    private String selectedText2;
    private String selectedText3;
    private String selectedText4;
    private String result_s;
    private TextView tresult;

    private String[][] twoDimArray;

    private CardView createCardView(String text, FlowLayout FL) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.cardview, FL, false);

        TextView textView = cardView.findViewById(R.id.textView);
        textView.setText(text);
        setButton(cardView, text);
        FL.addView(cardView);
        return cardView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity); // 레이아웃 파일 이름으로 변경

        // LinearLayout 가져오기
        FlowLayout FL = findViewById(R.id.FL);

        // 라디오 그룹 및 버튼 참조
        radioGroup1 = findViewById(R.id.radiogp);
        radioGroup2 = findViewById(R.id.radiogp2);
        radioGroup3 = findViewById(R.id.radiogp3);
        radioGroup4 = findViewById(R.id.radiogp4);
        nextButton = findViewById(R.id.next);
        tresult = findViewById(R.id.result);

        // "가자!" 버튼 클릭 이벤트 처리
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoDimArray = new String[][]{};
                if (FL != null) {
                    // 레이아웃의 모든 자식 뷰 제거
                    FL.removeAllViews();
                }

                // 각 라디오 그룹에서 선택된 라디오 버튼의 ID를 가져옴
                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                int selectedId4 = radioGroup4.getCheckedRadioButtonId();

                // 선택된 라디오 버튼의 ID를 사용하여 선택된 라디오 버튼의 텍스트를 가져옴
                selectedText1 = getRadioButtonText(selectedId1, radioGroup1);
                selectedText2 = getRadioButtonText(selectedId2, radioGroup2);
                selectedText3 = getRadioButtonText(selectedId3, radioGroup3);
                selectedText4 = getRadioButtonText(selectedId4, radioGroup4);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://203.234.62.169:5000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitService servicel = retrofit.create(RetrofitService.class);


                Call<PostResult> call = servicel.getPosts(selectedText1, selectedText2, selectedText3, selectedText4);

                call.enqueue(new Callback<PostResult>() {
                    @Override
                    public void onResponse(@NonNull Call<PostResult> call, @NonNull Response<PostResult> response) {
                        if(response.isSuccessful()) {
                            PostResult result = response.body();

                            try {
                                // 문자열 처리를 통해 '['와 ']'를 제거하고 ','로 split
                                result_s=result.toString();
                                if (result_s.equals("")) {
                                    tresult.setVisibility(view.VISIBLE);
                                    return;
                                }
                                else {
                                    if (tresult.getVisibility() == view.VISIBLE)
                                        tresult.setVisibility(view.GONE);
                                    String[] rows = result_s.substring(2, result_s.length() - 2).split(", ");

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
                            } catch (Exception e) {
                                // 예외 처리: 파싱 실패 시
                                e.printStackTrace();
                            }
                            for (int i = 0; i < twoDimArray.length; i++) {
                                createCardView(twoDimArray[i][0], FL);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<PostResult> call, Throwable t) {
                        String stackTrace = Log.getStackTraceString(t);
                        TextView tv = findViewById(R.id.textView);
                        tv.setText("데이터 불러오기 실패");
                    }
                });
            }
        });
    }
    private void setButton(CardView cv, String text) {
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SharedPreferences에 선택한 텍스트 저장
                SharedPreferences sharedPreferences = getSharedPreferences("SelectedText", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("text", text);
                editor.apply();

                Intent intent = new Intent(Activity.this, Activity_result.class);
                intent.putExtra("plz", text != null ? text : "명소");
                startActivity(intent);
            }
        });
    }
    // 라디오 버튼의 ID를 사용하여 라디오 버튼의 텍스트를 가져오는 메서드
    private String getRadioButtonText(@IdRes int radioButtonId, RadioGroup radioGroup) {
        if (radioButtonId == -1) {
            return "r"; // 선택된 라디오 버튼이 없을 경우 r 반환
        }
        RadioButton radioButton = findViewById(radioButtonId);
        String result = radioButton != null ? radioButton.getText().toString() : "r";
        return result.equals("선택 안함") ? "r" : result;
    }

}