package com.example.bai18_updateautocompleteatruntime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Student> {
    private Activity context;
    private int resourceId;
    private ArrayList<Student> arrStudent;

    public MyArrayAdapter(Activity context, int resourceId, ArrayList<Student> arrStudent)
    {
        super(context, resourceId, arrStudent);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.resourceId = resourceId;
        this.arrStudent = arrStudent;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        
        if (convertView == null)
        {
            convertView = context.getLayoutInflater().inflate(resourceId, null);
        }
        TextView txtMaTen = (TextView) convertView.findViewById(R.id.txtMaVaTen);
        TextView txtKhac = (TextView) convertView.findViewById(R.id.txtThongTinKhac);
        Student s = arrStudent.get(position);
        txtMaTen.setText(s.getId() + " - " + s.getName());
        SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        txtKhac.setText((s.isGender()? "Nữ - " : "Nam - ") + dft.format(s.getBirthday()) + s.getPlaceOfBirth());
        return convertView;
    }
    
    
}
