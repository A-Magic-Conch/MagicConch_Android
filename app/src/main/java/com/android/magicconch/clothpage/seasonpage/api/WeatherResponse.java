package com.android.magicconch.clothpage.seasonpage.api;

import java.util.List;

public class WeatherResponse {
    public Response response;

    public static class Response {
        public Body body;
    }

    public static class Body {
        public Items items;
    }

    public static class Items {
        public List<Item> item;
    }

    public static class Item {
        public String category;
        public String fcstValue;
    }
}