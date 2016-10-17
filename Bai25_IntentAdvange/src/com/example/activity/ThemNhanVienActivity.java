package com.example.activity;

import com.example.model.ChucVu;
import com.example.model.NhanVien;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ThemNhanVienActivity extends Activity {
    private Button btnXoaTrang, btnLuuNhanVien;
    private EditText editManv, editTenNv;
    private RadioButton radNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        getFormWidgets();
        addEvents();
    }

    private void addEvents() {
        // TODO Auto-generated method stub
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                doXoaTrang();
            }
        });
        
        btnLuuNhanVien.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                doLuuNhanVien();
            }
        });
    }

    protected void doLuuNhanVien() {
        // TODO Auto-generated method stub
        NhanVien nv = new NhanVien();
        nv.setMa(editManv.getText() + "");
        nv.setTen(editTenNv.getText() + "");
        nv.setChucvu(ChucVu.NhanVien);
        nv.setGioitinh(!radNam.isChecked());
        Intent i = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NHANVIEN", nv);
        i.putExtra("DATA", bundle);
        setResult(MainActivity.THEM_NHAN_VIEN_THANHCONG, i);
        finish();
    }

    protected void doXoaTrang() {
        // TODO Auto-generated method stub
        editManv.setText("");
        editTenNv.setText("");
        editManv.requestFocus();
    }

    private void getFormWidgets() {
        // TODO Auto-generated method stub
        btnXoaTrang = (Button) findViewById(R.id.btnxoatrang);
        btnLuuNhanVien = (Button) findViewById(R.id.btnluunv);
        editManv = (EditText) findViewById(R.id.editMaNV);
        editTenNv = (EditText) findViewById(R.id.editTenNV);
        radNam = (RadioButton) findViewById(R.id.radNam);
    }

}
