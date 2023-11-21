package com.android.magicconch.clothpage.seasonpage.clothaddpage;

public class ClothInfo {
    String clothName;
    String clothType;
    boolean isThin;
    boolean isMedium;
    boolean isThick;
    String description;

    public ClothInfo(String clothName, String clothType, boolean isThin, boolean isMedium, boolean isThick, String description) {
        this.clothName = clothName;
        this.clothType = clothType;
        this.isThin = isThin;
        this.isMedium = isMedium;
        this.isThick = isThick;
        this.description = description;
    }
}
