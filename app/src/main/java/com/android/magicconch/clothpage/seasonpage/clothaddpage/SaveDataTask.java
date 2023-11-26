package com.android.magicconch.clothpage.seasonpage.clothaddpage;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;

public class SaveDataTask extends AsyncTask<Void, Void, Void> {
    ClothInfo clothInfo;
    Context mContext;

    public SaveDataTask(Context context, ClothInfo clothInfo) {
        this.mContext = context;
        this.clothInfo = clothInfo;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Gson gson = new Gson();
        String clothInfoJson = gson.toJson(clothInfo);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Clothes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("clothInfo", clothInfoJson);
        editor.apply();

        return null;
    }
}
