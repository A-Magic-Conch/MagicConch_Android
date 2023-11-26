package com.android.magicconch.clothpage.seasonpage.clothaddpage;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private String[] imageNames;

    public ImageAdapter(Context context, String[] imageNames) {
        this.context = context;
        this.imageNames = imageNames;
    }

    @Override
    public int getCount() {
        return imageNames.length;
    }

    @Override
    public Object getItem(int position) {
        return imageNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(20, 20, 20, 20);
        } else {
            imageView = (ImageView) convertView;
        }

        int imageResId = context.getResources().getIdentifier(imageNames[position], "drawable", context.getPackageName());
        Glide.with(context).load(imageResId).into(imageView);
        return imageView;
    }

}
