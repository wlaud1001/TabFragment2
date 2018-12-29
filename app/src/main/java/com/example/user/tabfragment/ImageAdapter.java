package com.example.user.tabfragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //view의 width와 heigth설정 drawable크기에 상관없이 각 이미지 크기 조정
            imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
            //image가 center를 향해 잘리도록 선언
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //모든면에 대해 padding을 정의,
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public static Integer[] mThumbIds = {
            R.drawable.icon,R.drawable.s0,
            R.drawable.s0,R.drawable.s1,
            R.drawable.s2,R.drawable.s3,
            R.drawable.s4,R.drawable.icon,
            R.drawable.icon,R.drawable.icon,
            R.drawable.icon,R.drawable.s1,
            R.drawable.s0,R.drawable.s1,
            R.drawable.s2,R.drawable.s3,
            R.drawable.s4,R.drawable.icon,
            R.drawable.icon,R.drawable.icon,
            R.drawable.icon,R.drawable.s0,
            R.drawable.s0,R.drawable.s1,
            R.drawable.s2,R.drawable.s3,
            R.drawable.s4,R.drawable.icon,
            R.drawable.icon,R.drawable.icon
    };
}