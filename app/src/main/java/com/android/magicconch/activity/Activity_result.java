package com.android.magicconch.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.android.magicconch.R;
import com.android.magicconch.request.PostResult;
import com.android.magicconch.request.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_result extends android.app.Activity {
    private String result_s;
    private Button btn;
    private List<TextView> textViewList = new ArrayList<>();
    private List<CardView> cardViewList = new ArrayList<>();
    private String[][] twoDimArray;

    private WebView webView;

    private CardView createCardView(String text, LinearLayout LL) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.cardview2, LL, false);

        TextView textView = cardView.findViewById(R.id.textView);
        textView.setText(text);

        ImageView imageView = cardView.findViewById(R.id.imageView);
        imageView.setContentDescription("instagram");

        LL.addView(cardView);
        return cardView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Context cont = this;
        Intent intent = getIntent();
        String act = intent.getStringExtra("plz");

        btn = findViewById(R.id.next);

        if (act != null)
            btn.setText(act);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://203.234.62.169:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService servicel = retrofit.create(RetrofitService.class);
        Call<PostResult> call = servicel.getPosts(act != null ? act : "명소");

        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(@NonNull Call<PostResult> call, @NonNull Response<PostResult> response) {
                if(response.isSuccessful()) {
                    PostResult result = response.body();

                    try {
                        // 문자열 처리를 통해 '['와 ']'를 제거하고 ','로 split
                        result_s=result.toString();
                        if (result_s.equals("")) {
                            twoDimArray = new String[][]{{"없어용", "r", "r", "r", "r"}, {"행운의 액티비티 뽑기", "r", "r", "r", "r"}};
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
                    } catch (Exception e) {
                        // 예외 처리: 파싱 실패 시
                        e.printStackTrace();
                    }
                    LinearLayout LL = findViewById(R.id.LL);
                    for (int i = 0; i < twoDimArray.length; i++) {
                        CardView cardView = createCardView(twoDimArray[i][0], LL);
                    }

//                    for (int i = 0; i < twoDimArray.length-1; i++) {
//                        int finalI = i;
//                        cardViewList.get(i).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                ArrayList<String> ranact = new ArrayList<>();
//                                ranact.add("r");
//                                ranact.add("r");
//                                ranact.add("r");
//                                ranact.add("r");
//
//                                Intent intent = new Intent(Activity_result.this, Activity_result.class);
//                                intent.putStringArrayListExtra("plz", ranact);
//                                finish();
//                                startActivity(intent);
//                            }
//                        });
//                    }
                }
            }
            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                String stackTrace = Log.getStackTraceString(t);
                TextView tv = findViewById(R.id.textView);
                tv.setText("데이터 불러오기 실패");
            }
        });

        // web view 설정(크롬)
        webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(String.format("https://www.google.co.kr/maps/search/군산 %s", act));
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)&& webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}