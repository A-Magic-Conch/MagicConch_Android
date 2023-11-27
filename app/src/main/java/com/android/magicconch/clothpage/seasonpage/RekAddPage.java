package com.android.magicconch.clothpage.seasonpage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.magicconch.R;
import com.android.magicconch.clothpage.seasonpage.api.WeatherApi;
import com.android.magicconch.clothpage.seasonpage.api.WeatherResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RekAddPage extends AppCompatActivity {
    private TextView tvSky;
    private TextView tvTemperature;
    private ImageView imageViewWeatherIcon;
    private ImageView imageViewTopClothes;
    private ImageView imageViewBottomClothes;

    int[] summerTopClothes = {
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10
    };

    int[] summerBottomClothes = {
            R.drawable.sp1, R.drawable.sp2, R.drawable.sp3, R.drawable.sp4,
            R.drawable.sp5, R.drawable.sp6, R.drawable.sp7, R.drawable.sp8
    };

    int[] topClothes = {
            R.drawable.hood1, R.drawable.hood2, R.drawable.hood3, R.drawable.hood4,
            R.drawable.mantoman1, R.drawable.mantoman2, R.drawable.mantoman3, R.drawable.mantoman4
    };

    int[] bottomClothes = {
            R.drawable.pants1, R.drawable.pants2, R.drawable.pants3, R.drawable.pants4,
            R.drawable.slacks1, R.drawable.slacks2, R.drawable.slacks3, R.drawable.slacks4
    };

    int[] coldTopClothes = {
            R.drawable.padding1, R.drawable.padding2, R.drawable.padding3, R.drawable.padding4,
            R.drawable.mus1, R.drawable.mus2, R.drawable.coat1, R.drawable.coat2
    };

    int[] coldBottomClothes = {
            R.drawable.geemo1, R.drawable.geemo2, R.drawable.geemo3, R.drawable.geemo4,
            R.drawable.pants1, R.drawable.pants2, R.drawable.pants3, R.drawable.pants4,
            R.drawable.slacks1, R.drawable.slacks2, R.drawable.slacks3, R.drawable.slacks4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rekaddpage);
        setTitle("날씨 정보");

        tvSky = findViewById(R.id.tv_sky);
        tvTemperature = findViewById(R.id.tv_temperature);
        imageViewWeatherIcon = findViewById(R.id.imageView_weatherIcon);
        imageViewTopClothes = findViewById(R.id.imageView_topClothes);
        imageViewBottomClothes = findViewById(R.id.imageView_bottomClothes);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocalDateTime now = LocalDateTime.now().minusHours(6);
        String baseDate = now.truncatedTo(ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeather(
                "y22xUV9DMD/dNGMQQurE6tO0slcOoTIZiiiaXT++N3HT08IfBj3VvNqlD7ynr/IbN1YkB+2tUGWoqxWW/X5uRg==",
                "55", "92", "14", "20231126","1400", "json");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (!response.isSuccessful()) {
                    tvSky.setText("Code: " + response.code());
                    return;
                }

                WeatherResponse weatherResponse = response.body();
                String sky = "";
                String temperature = "";
                for (WeatherResponse.Item item : weatherResponse.response.body.items.item) {
                    if (item.category.equals("SKY")) {
                        if (item.fcstValue.equals("1")) {
                            sky = "맑음 ";
                            imageViewWeatherIcon.setImageResource(R.drawable.sun);
                        } else if (item.fcstValue.equals("2")) {
                            sky = "비 ";
                            imageViewWeatherIcon.setImageResource(R.drawable.rain);
                        } else if (item.fcstValue.equals("3")) {
                            sky = "구름많음 ";
                            imageViewWeatherIcon.setImageResource(R.drawable.suncloud);
                        } else if (item.fcstValue.equals("4")) {
                            sky = "흐림 ";
                            imageViewWeatherIcon.setImageResource(R.drawable.cloud);
                        }
                    }

                    if (item.category.equals("TMP")) {
                        temperature = item.fcstValue + "℃ ";
                    }
                }

                tvSky.setText(sky);
                tvTemperature.setText(temperature);

                double temp = Double.parseDouble(temperature.replace("℃ ", ""));
                Random random = new Random();

                if (temp >= 10 && temp <= 20) {
                    int topClothImage = topClothes[random.nextInt(topClothes.length)];
                    int bottomClothImage = bottomClothes[random.nextInt(bottomClothes.length)];

                    imageViewTopClothes.setImageResource(topClothImage);
                    imageViewBottomClothes.setImageResource(bottomClothImage);
                } else if(temp <= 9) {
                    int topClothImage = coldTopClothes[random.nextInt(coldTopClothes.length)];
                    int bottomClothImage = coldBottomClothes[random.nextInt(coldBottomClothes.length)];

                    imageViewTopClothes.setImageResource(topClothImage);
                    imageViewBottomClothes.setImageResource(bottomClothImage);
                }  else if(temp >= 20 && temp <= 40) {
                    int topClothImage = summerTopClothes[random.nextInt(summerTopClothes.length)];
                    int bottomClothImage = summerBottomClothes[random.nextInt(summerBottomClothes.length)];

                    imageViewTopClothes.setImageResource(topClothImage);
                    imageViewBottomClothes.setImageResource(bottomClothImage);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                tvSky.setText(t.getMessage());
            }
        });
    }
}
