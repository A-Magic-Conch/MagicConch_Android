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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RekAddPage extends AppCompatActivity {
    private TextView tvSky;
    private TextView tvTemperature;
    private ImageView imageViewWeatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rekaddpage);
        setTitle("날씨 정보");

        tvSky = findViewById(R.id.tv_sky);
        tvTemperature = findViewById(R.id.tv_temperature);
        imageViewWeatherIcon = findViewById(R.id.imageView_weatherIcon);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocalDateTime now = LocalDateTime.now().minusHours(6);
        String baseDate = now.truncatedTo(ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeather(
                "y22xUV9DMD/dNGMQQurE6tO0slcOoTIZiiiaXT++N3HT08IfBj3VvNqlD7ynr/IbN1YkB+2tUGWoqxWW/X5uRg==",
                "55", "92", "14", "20231118","1400", "json");

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
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                tvSky.setText(t.getMessage());
            }
        });
    }
}
