package com.example.adapter;

import java.util.ArrayList;

import com.example.model.NhanVien;
import com.example.model.PhongBan;

//import android.R;
import com.example.activity.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PhongBanAdapter extends ArrayAdapter<PhongBan> {
    Activity context;
    int layoutId;
    ArrayList<PhongBan> arrPhongBan;

    public PhongBanAdapter(Activity context, int textViewResourceId,
            ArrayList<PhongBan> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutId = textViewResourceId;
        this.arrPhongBan = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        convertView = context.getLayoutInflater().inflate(layoutId, null);
        TextView txtpb = (TextView) convertView.findViewById(R.id.txtShortInfor);
        TextView txtmotapb = (TextView) convertView.findViewById(R.id.txtDetailInfor);
        // lấy phòng ban thứ position
        PhongBan pb = arrPhongBan.get(position);
        txtpb.setText(pb.toString());
        
        // Kiem tra pho phong, truong phong
        String strMota = "";
        String tp = "Trưởng phòng [Chưa có]";
        NhanVien nv = pb.getTruongPhong();
        if (nv != null)
        {
            tp = "Trưởng phòng [ " + nv.getTen() + " ]";
        }
        
        ArrayList<NhanVien> pp = pb.getPhoPhong();
        String strpp = "Phó phòng [Chưa có]";
        if (pp.size() > 0)
        {
            strpp = "Phó phòng:\n";
            for (int i = 0; i < pp.size(); i++)
                strpp += (i+1) + ". " + pp.get(i).getTen() + "\n";
        }
        strMota = tp + "\n" + strpp;
        txtmotapb.setText(strMota);
        return convertView;
    }
    
    

}
