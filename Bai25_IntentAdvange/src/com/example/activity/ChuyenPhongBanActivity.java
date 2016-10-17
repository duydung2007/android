package com.example.activity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.adapter.PhongBanAdapter;
import com.example.model.NhanVien;
import com.example.model.PhongBan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;

public class ChuyenPhongBanActivity extends Activity {
    ListView lvPb;
    private static ArrayList<PhongBan> arrPhongBan = null;
    ArrayAdapter<PhongBan> adapter;
    ImageButton btnApply;
    NhanVien nv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_phong_ban);
        getFormWidgets();
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        nv = (NhanVien) b.getSerializable("NHANVIEN");
    }

    private void getFormWidgets() {
        // TODO Auto-generated method stub
        lvPb = (ListView) findViewById(R.id.lvphongban);
        btnApply = (ImageButton) findViewById(R.id.imgapply);
        arrPhongBan = MainActivity.getListPhongBan();
        // adapter = new PhongBanAdapter(this, R.layout.layout_item_custom,
        // arrPhongBan);
        adapter = new ArrayAdapter<PhongBan>(this,
                android.R.layout.simple_list_item_single_choice, arrPhongBan);
        lvPb.setAdapter(adapter);

        lvPb.setOnItemClickListener(new OnItemClickListener() {
            boolean somethingChecked = false;

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // TODO Auto-generated method stub
                // hiển nhiên View arg1 là CheckedTextView
                if (somethingChecked) {
                    CheckedTextView cv = (CheckedTextView) arg1;
                    cv.setChecked(false);
                }
                CheckedTextView cv = (CheckedTextView) arg1;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrPhongBan.get(arg2).themNv(nv);
                }
                somethingChecked = true;
            }
        });

        // khi chọn nút Apply thì tiến hành đóng màn hình này
        // và truyền lệnh về cho DanhSachNhanVienACtivity
        btnApply.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                doApply();

            }
        });
    }

    protected void doApply() {
        // TODO Auto-generated method stub
        setResult(MainActivity.CHUYENPHONG_THANHCONG);
        finish();
    }
}
