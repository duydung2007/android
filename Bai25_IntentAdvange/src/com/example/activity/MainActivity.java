package com.example.activity;

import java.util.ArrayList;

import com.example.adapter.PhongBanAdapter;
import com.example.model.ChucVu;
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
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity {
    public static final int MO_ACTIVITY_THEM_NHAN_VIEN = 1;
    public static final int MO_ACTIVITY_SUA_NHAN_VIEN = 2;
    public static final int THEM_NHAN_VIEN_THANHCONG = 3;
    public static final int SUA_NHAN_VIEN_THANHCONG = 4;
    public static final int XEM_DS_NHAN_VIEN = 5;
    public static final int CAPNHAT_DS_NHAN_VIEN_THANHCONG = 6;
    public static final int MO_ACTIVITY_THIET_LAP_TP_PP = 7;
    public static final int THIET_LAP_TP_PP_THANHCONG = 8;
    public static final int MO_ACTIVITY_CHUYENPHONG = 9;
    public static final int CHUYENPHONG_THANHCONG = 10;

    private Button btnLuuPb;
    private EditText editMapb, editTenpb;
    private ListView lvpb;

    private static ArrayList<PhongBan> arrPhongBan = new ArrayList<PhongBan>();
    private PhongBanAdapter adapterPb = null;
    private PhongBan pbSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        addEvents();
        fakeData();
    }

    private void getFormWidgets() {
        // TODO Auto-generated method stub
        btnLuuPb = (Button) findViewById(R.id.btnluupb);
        editMapb = (EditText) findViewById(R.id.editmapb);
        editTenpb = (EditText) findViewById(R.id.editTenpb);
        lvpb = (ListView) findViewById(R.id.lvphongban);

        // khởi tạo đối tượng phòng ban adapter
        // dùng layout_item_custom.xml
        adapterPb = new PhongBanAdapter(this, R.layout.layout_item_custom,
                arrPhongBan);
        lvpb.setAdapter(adapterPb);
        registerForContextMenu(lvpb);
    }

    /**
     * hàm gán sự kiện cho các control: button, ListView
     */
    private void addEvents() {
        // TODO Auto-generated method stub
        btnLuuPb.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                doLuuPhongBan();
            }
        });

        lvpb.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                pbSelected = arrPhongBan.get(arg2);
                return false;
            }
        });
    }

    private void fakeData() {
        // TODO Auto-generated method stub
        NhanVien nv = null;
        PhongBan pb = new PhongBan("pb1", "Kỹ thuật");
        nv = new NhanVien("m4", "Đoàn Ái Nương", true);
        nv.setChucvu(ChucVu.TruongPhong);
        pb.themNv(nv);
        nv = new NhanVien("m5", "Trần Đạo Đức", false);
        nv.setChucvu(ChucVu.PhoPhong);
        pb.themNv(nv);
        nv = new NhanVien("m6", "Nguyễn Đại Tài", false);
        nv.setChucvu(ChucVu.PhoPhong);
        pb.themNv(nv);
        arrPhongBan.add(pb);
        pb = new PhongBan("pb2", "Dịch vụ");
        arrPhongBan.add(pb);
        pb = new PhongBan("pb3", "Truyền thông");
        arrPhongBan.add(pb);
        nv = new NhanVien("m1", "Nguyễn Văn Tẻo", false);
        nv.setChucvu(ChucVu.NhanVien);
        pb.themNv(nv);
        nv = new NhanVien("m2", "Nguyễn Thị Téo", true);
        nv.setChucvu(ChucVu.TruongPhong);
        pb.themNv(nv);
        nv = new NhanVien("m3", "Nguyễn Văn Teo", false);
        nv.setChucvu(ChucVu.NhanVien);
        pb.themNv(nv);

        adapterPb.notifyDataSetChanged();
    }

    protected void doLuuPhongBan() {
        // TODO Auto-generated method stub
        String maPhongBan = editMapb.getText() + "";
        String tenPhongBan = editTenpb.getText() + "";
        PhongBan pb = new PhongBan(maPhongBan, tenPhongBan);
        arrPhongBan.add(pb);
        adapterPb.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_phongban, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
        case R.id.mnuthemnv:
            doThemNhanVien();
            break;
        case R.id.mnudanhsachnv:
            doDanhSachNhanVien();
            break;
        case R.id.mnutruongphong:
            doThietLapLanhDao();
            break;
        case R.id.mnuxoapb:
            doXoaPhongBan();
            break;
        }
        return super.onContextItemSelected(item);
    }

    private void doXoaPhongBan() {
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa phòng ban");
        builder.setMessage("Bạn có chắc chắn muốn xóa [ " + pbSelected.getTen()
                + " ]");
        builder.setIcon(android.R.drawable.ic_input_delete);
        builder.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                arrPhongBan.remove(pbSelected);
                adapterPb.notifyDataSetChanged();
            }
        });
        builder.show();
    }

    private void doThietLapLanhDao() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this, ThietLapTruongPhongActivity.class);
        Bundle bundel = new Bundle();
        bundel.putSerializable("PHONGBAN", pbSelected);
        i.putExtra("DATA", bundel);
        startActivityForResult(i, MO_ACTIVITY_THIET_LAP_TP_PP);
    }

    private void doDanhSachNhanVien() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this, DanhSachNhanVienActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PHONGBAN", pbSelected);
        i.putExtra("DATA", bundle);
        startActivityForResult(i, XEM_DS_NHAN_VIEN);
    }

    private void doThemNhanVien() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this, ThemNhanVienActivity.class);
        startActivityForResult(i, MO_ACTIVITY_THEM_NHAN_VIEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        // màn hình thêm mới nhân viên trả kết quả về
        if (resultCode == THEM_NHAN_VIEN_THANHCONG) {
            Bundle bundle = data.getBundleExtra("DATA");
            NhanVien nv = (NhanVien) bundle.getSerializable("NHANVIEN");
            pbSelected.themNv(nv);
            adapterPb.notifyDataSetChanged();
        } else if (resultCode == CAPNHAT_DS_NHAN_VIEN_THANHCONG) {
            Bundle b = data.getBundleExtra("DATA");
            PhongBan pb = (PhongBan) b.getSerializable("PHONGBAN");
            pbSelected.getListNhanVien().clear();
            pbSelected.getListNhanVien().addAll(pb.getListNhanVien());
            adapterPb.notifyDataSetChanged();
        }
        // Màn hình thiết lập lạnh đão/ cập nhật danh sách trả
        // kết quả về
        else if (resultCode == THIET_LAP_TP_PP_THANHCONG
                || resultCode == CAPNHAT_DS_NHAN_VIEN_THANHCONG) {
            // Cách lấy dữ liệu đã học rồi, ko nói lại
            Bundle bundle = data.getBundleExtra("DATA");
            PhongBan pb = (PhongBan) bundle.getSerializable("PHONGBAN");
            // đơn thuần là xóa danh sách cũ
            pbSelected.getListNhanVien().clear();
            // rồi cập nhật lại toàn bộ danh sách mới
            // sinh viên có thể chọn giải pháp thông minh hơn
            // tức là phần tử nào bị ảnh hưởng thì xử lý phần tử đó
            pbSelected.getListNhanVien().addAll(pb.getListNhanVien());
            adapterPb.notifyDataSetChanged();
        }
    }

    /**
     * Tôi cố tình cung cấp hàm này để ở các Activity khác đều có thể truy suất
     * được danh sách phòng ban tổng thể
     * 
     * @return
     */
    public static ArrayList<PhongBan> getListPhongBan() {
        return arrPhongBan;
    }
}
