package com.example.bai19_gridview_displayimage;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MyImageAdapter extends BaseAdapter {
    Activity context;
    Integer arr[];

    public MyImageAdapter(Activity context, Integer[] arr) {
        super();
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arr.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ImageView imgView;
        if (arg1 == null)
        {
            imgView = new ImageView(context);
            imgView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imgView = (ImageView) arg1;
        }
        imgView.setImageResource(arr[arg0]);
        
        return imgView;
    }
}
