package com.example.activity;

import com.example.model.NhanVien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class SuaNhanVienActivity extends Activity {
    EditText editMa, editTen;
    RadioButton radNam;
    Button btnClear, btnSave;
    NhanVien nv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        getFormWidgets();
        getDefaultData();
        addEvents();
    }

    private void getFormWidgets() {
        // TODO Auto-generated method stub
        editMa = (EditText) findViewById(R.id.editMaNV);
        editTen = (EditText) findViewById(R.id.editTenNV);
        btnClear = (Button) findViewById(R.id.btnxoatrang);
        btnSave = (Button) findViewById(R.id.btnluunv);
        radNam = (RadioButton) findViewById(R.id.radNam);

        editMa.setEnabled(false);
        editTen.requestFocus();
    }

    private void getDefaultData() {
        // TODO Auto-generated method stub
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        nv = (NhanVien) b.getSerializable("NHANVIEN");
        editMa.setText(nv.getMa());
        editTen.setText(nv.getTen());
        radNam.setChecked(true);
        if (nv.isGioitinh())
            radNam.setChecked(false);
    }

    private void addEvents() {
        // TODO Auto-generated method stub
        btnClear.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                editTen.setText("");
                editTen.requestFocus();
            }
        });
        
        btnSave.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = getIntent();
                nv.setTen(editTen.getText() + "");
                nv.setGioitinh(!radNam.isChecked());
                Bundle b = new Bundle();
                b.putSerializable("NHANVIEN", nv);
                i.putExtra("DATA", b);
                setResult(MainActivity.SUA_NHAN_VIEN_THANHCONG, i);
                finish();
            }
        });
    }

}
