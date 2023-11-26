package com.android.magicconch.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// DTO 모델 - PostResult Class 선언
public class PostResult {
    @SerializedName("result")
    public String result;
    @Override
    public String toString() {
        return result;
    }
}