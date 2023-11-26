package com.android.magicconch.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("api")
    Call<PostResult> getPosts(
            @Query("p1") String p1,
            @Query("p2") String p2,
            @Query("p3") String p3,
            @Query("p4") String p4,
            @Query("p5") String p5,
            @Query("p6") String p6,
            @Query("p7") String p7
    );
}