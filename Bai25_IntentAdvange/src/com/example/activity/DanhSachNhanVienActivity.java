package com.example.activity;

import java.util.ArrayList;

import com.example.adapter.NhanVienAdapter;
import com.example.model.NhanVien;
import com.example.model.PhongBan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemLongClickListener;

public class DanhSachNhanVienActivity extends Activity {
    TextView txtmsg;
    ImageButton btnback;
    ListView lvNhanvien;
    ArrayList<NhanVien> arrNhanvien = null;
    // Nhân viên Adapter để hiển thị thông tin
    // và chi tiết : chức vụ, giới tính
    NhanVienAdapter adapter = null;
    PhongBan pb = null;
    private NhanVien nvSelected = null;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhan_vien);
        txtmsg = (TextView) findViewById(R.id.txtmsg);
        btnback = (ImageButton) findViewById(R.id.btnback);
        lvNhanvien = (ListView) findViewById(R.id.lvnhanvien);
        getDataFromMain();
        addEvents();
        registerForContextMenu(lvNhanvien);
    }

    private void getDataFromMain() {
        // TODO Auto-generated method stub
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        pb = (PhongBan) b.getSerializable("PHONGBAN");
        arrNhanvien = pb.getListNhanVien();
        adapter = new NhanVienAdapter(this, R.layout.layout_item_custom,
                arrNhanvien);
        lvNhanvien.setAdapter(adapter);
        txtmsg.setText("Danh sách nhân viên [ " + pb.getTen() + " ]");
    }

    private void addEvents() {
        // TODO Auto-generated method stub
        btnback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                doUpdateToMain();
            }
        });

        lvNhanvien.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                nvSelected = arrNhanvien.get(arg2);
                position = arg2;
                return false;
            }
        });
    }

    protected void doUpdateToMain() {
        // TODO Auto-generated method stub
        Intent i = getIntent();
        Bundle b = new Bundle();
        b.putSerializable("PHONGBAN", pb);
        i.putExtra("DATA", b);
        setResult(MainActivity.CAPNHAT_DS_NHAN_VIEN_THANHCONG, i);
        finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_nhanvien, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
        case R.id.mnusuanv:
            doSuaNhanVien();
            break;
        case R.id.mnuchuyenpb:
            doChuyenPhongBan();
            break;
        case R.id.mnuxoanv:
            doXoaNhanVien();
            break;

        default:
            break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, i);
        if (resultCode == MainActivity.SUA_NHAN_VIEN_THANHCONG)
        {
            Bundle b = i.getBundleExtra("DATA");
            NhanVien nv = (NhanVien) b.getSerializable("NHANVIEN");
            arrNhanvien.set(position, nv);
            adapter.notifyDataSetChanged();
        }
        else if (resultCode == MainActivity.CHUYENPHONG_THANHCONG)
        {
            arrNhanvien.remove(nvSelected);
            adapter.notifyDataSetChanged();
        }
    }

    private void doXoaNhanVien() {
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hỏi xóa !");
        builder.setMessage("Bạn có chắc chắn muốn xóa [ " + nvSelected.getTen() + " ]");
        builder.setIcon(android.R.drawable.ic_input_delete);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                arrNhanvien.remove(nvSelected);
                adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }

    private void doChuyenPhongBan() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this, ChuyenPhongBanActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("NHANVIEN", nvSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, MainActivity.MO_ACTIVITY_CHUYENPHONG);
    }

    private void doSuaNhanVien() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this, SuaNhanVienActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("NHANVIEN", nvSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, MainActivity.MO_ACTIVITY_SUA_NHAN_VIEN);
    }
}
