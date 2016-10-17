package com.example.adapter;

import java.util.ArrayList;

import com.example.model.NhanVien;
import com.example.activity.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int layoutId;
    ArrayList<NhanVien> arrNhanVien;
    public NhanVienAdapter(Activity context,
            int layoutId, ArrayList<NhanVien> arrNhanVien) {
        super(context, layoutId, arrNhanVien);
        this.context = context;
        this.layoutId = layoutId;
        this.arrNhanVien = arrNhanVien;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        convertView = context.getLayoutInflater().inflate(layoutId, null);
        
        TextView txtnv = (TextView) convertView.findViewById(R.id.txtShortInfor);
        TextView txtmotanv = (TextView) convertView.findViewById(R.id.txtDetailInfor);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgview);
        // Lấy nhân viên thứ position
        NhanVien nv = arrNhanVien.get(position);
        txtnv.setText(nv.toString());
        String strMota = "";
        String cv = "Chức vụ: " + nv.getChucvu().getChucVu();
        String gt = "Giới tính: " + (nv.isGioitinh()? "Nữ" : "Nam");
        img.setImageResource(R.drawable.girlicon);
        if (!nv.isGioitinh())
            img.setImageResource(R.drawable.boyicon);
        strMota = cv + "\n" + gt;
        txtmotanv.setText(strMota);
        return convertView;
    }
    

}
