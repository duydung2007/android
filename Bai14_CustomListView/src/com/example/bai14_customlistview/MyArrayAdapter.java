package com.example.bai14_customlistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Employee> {
    Activity context;
    int layoutId;
    ArrayList<Employee> myArray = null;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Employee> arr) {
        super(context, layoutId, arr);
        // TODO Auto-generated constructor stub
        this.context= context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        
        if (myArray.size() > 0 && position >= 0)
        {
            final TextView txtDisplay = (TextView) convertView.findViewById(R.id.txtitem);
            final Employee emp = myArray.get(position);
            txtDisplay.setText(emp.getName());
            final ImageView imgitem = (ImageView) convertView.findViewById(R.id.imgitem);
            if (emp.isGender())
            {
                imgitem.setImageResource(R.drawable.girlicon);
            }
            else
            {
                imgitem.setImageResource(R.drawable.boyicon);
            }
            
        }
        return convertView;
    }
}
