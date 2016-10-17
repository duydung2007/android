package com.example.bai18_updateautocompleteatruntime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    ListView lvSinhVien;
    MyArrayAdapter adapterSinhVien;
    ArrayList<Student> arrSinhVien = new ArrayList<Student>();
    EditText editMa, editTen, editNamSinh;
    CheckBox chkGender;
    Button btnNamSinh, btnNhapSV;

    AutoCompleteTextView autoTextNS;
    ArrayList<String> arrAuto = new ArrayList<String>();
    ArrayAdapter<String> adapterAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getFormWidgets();
        addEventsForWidgets();
        fakeData();
    }

    public void getFormWidgets() {
        editMa = (EditText) findViewById(R.id.editMa);
        editTen = (EditText) findViewById(R.id.editTen);
        editNamSinh = (EditText) findViewById(R.id.editNgaySinh);
        chkGender = (CheckBox) findViewById(R.id.chkGt);
        autoTextNS = (AutoCompleteTextView) findViewById(R.id.autoCompleteNS);
        btnNamSinh = (Button) findViewById(R.id.btnNgaySinh);
        btnNhapSV = (Button) findViewById(R.id.btnNhap);

        lvSinhVien = (ListView) findViewById(R.id.lvsinhvien);
        adapterSinhVien = new MyArrayAdapter(this,
                R.layout.sinhvien_item_layout, arrSinhVien);
        lvSinhVien.setAdapter(adapterSinhVien);
    }

    /**
     * Hàm thiết lập sự kiện cho Button
     */
    public void addEventsForWidgets() {
        btnNamSinh.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                processBirthday();
            }
        });
        btnNhapSV.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                processInput();
            }
        });
    }

    public void processBirthday() {
        OnDateSetListener callBack = new OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) {
                // TODO Auto-generated method stub
                editNamSinh
                        .setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }
        };
        // Ở đây Tôi chưa xử lý lấy ngày tháng năm trên EditText đưa vào
        // DatePicker
        // Bạn tự làm
        DatePickerDialog dateDialog = new DatePickerDialog(this, callBack,
                1990, 4, 1);
        dateDialog.setTitle("Birthday");
        dateDialog.show();
    }

    /**
     * Hàm xử lý nhập dữ liệu từ giao diện
     */
    public void processInput() {
        String ma = editMa.getText() + "";
        String ten = editTen.getText() + "";
        String ns = editNamSinh.getText() + "";
        String nois = autoTextNS.getText() + "";
        boolean gt = chkGender.isSelected();
        SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault());
        try {
            Student s = new Student(ma, ten, gt, dft.parse(ns), nois);
            arrSinhVien.add(s);
            // Thêm mới xong thì gọi hàm dưới cập nhập lại giao diện
            adapterSinhVien.notifyDataSetChanged();
            // Xử lý cho Autocomplete về nơi sinh
            processAutoComplete(nois);
        } catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Hàm xử lý Autocomplete Nơi sinh
     * 
     * @param data
     */
    public void processAutoComplete(String data) {
        // Chạy từ đầu chí cuối nếu trùng thì thoát khỏi hàm
        for (int i = 0; i < arrAuto.size(); i++) {
            String x = arrAuto.get(i);
            if (x.trim().equalsIgnoreCase(data.trim()))
                return;
        }
        // nếu xuống đây được tức là chưa tồn tại-> đừa vào arrAuto
        arrAuto.add(data);
        // Đưa vào ADapter
        adapterAuto = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrAuto);
        // đưa Adapter vào AutoComplete
        autoTextNS.setAdapter(adapterAuto);
    }

    @SuppressWarnings("deprecation")
    public void fakeData() {
        Student s1 = new Student("1", "Đoàn Ái Nương", true, new Date(
                1980 - 1900, 2, 2), "TP. Hồ Chí Minh");
        Student s2 = new Student("2", "Nguyễn Thùy Trang", true, new Date(
                1982 - 1900, 3, 3), "Lâm Đồng");
        Student s3 = new Student("3", "Hoàng Văn Phúc", false, new Date(
                1970 - 1900, 4, 4), "Hà Nội");
        Student s4 = new Student("4", "Đinh Hồng Lợi", false, new Date(
                1972 - 1900, 4, 4), "Bắc Giang");
        Student s5 = new Student("5", "Nguyễn Hoàng Uyên", true, new Date(
                1970 - 1900, 4, 4), "Huê");
        arrSinhVien.add(s1);
        arrSinhVien.add(s2);
        arrSinhVien.add(s3);
        arrSinhVien.add(s4);
        arrSinhVien.add(s5);
        adapterSinhVien.notifyDataSetChanged();
    }
}
