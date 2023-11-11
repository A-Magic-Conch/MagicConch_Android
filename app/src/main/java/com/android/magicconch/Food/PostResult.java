package com.android.magicconch.Food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// DTO 모델 - PostResult Class 선언
public class PostResult {
    @SerializedName("result")
    private String result;
    @Override
    public String toString() {
        return "결과: " + result;
    }
}