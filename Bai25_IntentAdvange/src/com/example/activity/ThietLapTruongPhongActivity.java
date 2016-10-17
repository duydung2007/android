package com.example.activity;

import java.util.ArrayList;

import com.example.model.ChucVu;
import com.example.model.NhanVien;
import com.example.model.PhongBan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;

public class ThietLapTruongPhongActivity extends Activity {
    ListView lvtruongphong, lvphophong;
    ArrayList<NhanVien> arrNvForTP = new ArrayList<NhanVien>();
    ArrayAdapter<NhanVien> adapterForTP;
    ArrayList<NhanVien> arrNvForPP = new ArrayList<NhanVien>();
    ArrayAdapter<NhanVien> adapterForPP;
    ImageButton btnApply;
    int lastChecked = -1;
    PhongBan pb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap_truong_phong);
        getFormWidgets();
    }

    private void getFormWidgets() {
        // TODO Auto-generated method stub
        lvtruongphong = (ListView) findViewById(R.id.lvtruongphong);
        lvtruongphong.setTextFilterEnabled(true);
        lvtruongphong.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvtruongphong.setOnItemClickListener(new OnItemClickListener() {
            boolean somethingChecked = false;

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // TODO Auto-generated method stub
                arrNvForTP.get(arg2).setChucvu(ChucVu.TruongPhong);
                if (somethingChecked) {
                    CheckedTextView cv = (CheckedTextView) arg1;
                    cv.setChecked(false);
                }
                CheckedTextView cv = (CheckedTextView) arg1;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrNvForTP.get(arg2).setChucvu(ChucVu.TruongPhong);
                } else {
                    arrNvForTP.get(arg2).setChucvu(ChucVu.NhanVien);
                }
                lastChecked = arg2;
                somethingChecked = true;
            }
        });

        lvphophong = (ListView) findViewById(R.id.lvphophong);
        lvphophong.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                CheckedTextView cv = (CheckedTextView) arg1;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrNvForPP.get(arg2).setChucvu(ChucVu.PhoPhong);
                } else {
                    cv.setChecked(false);
                    arrNvForPP.get(arg2).setChucvu(ChucVu.NhanVien);
                }
            }
        });

        adapterForTP = new ArrayAdapter<NhanVien>(this,
                android.R.layout.simple_list_item_single_choice, arrNvForTP);
        adapterForPP = new ArrayAdapter<NhanVien>(this,
                android.R.layout.simple_list_item_multiple_choice, arrNvForPP);
        lvtruongphong.setAdapter(adapterForTP);
        lvphophong.setAdapter(adapterForPP);
        // Lấy được phòng ban gửi qua từ MainActivity
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("DATA");
        pb = (PhongBan) bundle.getSerializable("PHONGBAN");
        addNvToListTP(pb);
        addNvToListTP(pb);
        adapterForTP.notifyDataSetChanged();
        adapterForPP.notifyDataSetChanged();

        btnApply = (ImageButton) findViewById(R.id.imgapply);
        btnApply.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                doApply();

            }
        });
    }
    
    protected void doApply() {
        // TODO Auto-generated method stub
        Intent i = getIntent();
        Bundle b = new Bundle();
        b.putSerializable("PHONGBAN", pb);
        i.putExtra("DATA", b);
        setResult(MainActivity.THIET_LAP_TP_PP_THANHCONG, i);
        finish();
    }

    /**
     * duyệt toàn bộ nhân viên vào danh sách ứng viên Trưởng phòng
     * 
     * @param pb
     */
    public void addNvToListTP(PhongBan pb) {
        arrNvForTP.clear();
        for (NhanVien nv : pb.getListNhanVien()) {
            arrNvForTP.add(nv);
        }
    }

    /**
     * duyệt toàn bộ nhân viên vào danh sách ứng viên phó phòng
     * 
     * @param pb
     */
    public void addNvToListPP(PhongBan pb) {
        arrNvForPP.clear();
        for (NhanVien nv : pb.getListNhanVien()) {
            arrNvForPP.add(nv);
        }
    }
}
