package com.example.bai19_gridview_displayimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {
    TextView tvMsg;
    GridView gv;
    TextView tvSoloMsg;
    // mảng lưu danh sách các id hình ảnh có sẵn
    Integer[] mThumbIds;
    // Adapter cho GridView
    MyImageAdapter adapter = null;
    // Vì không tạo thêm 1 Activity nên lấy luôn 2 Id ở bên solo_picture.xml
    ImageView ivSoloPicture;
    Button btnBack;
    // Lưu Bundle backup cho MainActivity
    Bundle myBackupBundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Lưu savedInstanceState trước vào myBackupBundle
        myBackupBundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        // gán mảng các Id hình ảnh cho mThumbIds
        mThumbIds = new Integer[] { R.drawable.image1, R.drawable.image2,
                R.drawable.image3, R.drawable.image4, R.drawable.image5,
                R.drawable.image6, R.drawable.ic_launcher, R.drawable.lifecycle };
        gv = (GridView) findViewById(R.id.gridView1);
        // thiết lập Datasource cho Adapter
        adapter = new MyImageAdapter(this, mThumbIds);
        // gán Adapter vào Gridview
        gv.setAdapter(adapter);
        // thiết lập sự kiện để mở từng hình ảnh chitiết
        gv.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // gọi hàm xem hình ảnh chi tiết tại ví trí thứ arg2
        showdetail(arg2);
    }

    public void showdetail(int posistion) {
        // Không mở Activity mới mà chỉ thiết lập lại Layout
        setContentView(R.layout.solo_picture);
        // Vừa gọi hàm trên thì màn hình sẽ thay đổi qua cái mới
        // ta lấy các control trong layout mới này
        tvSoloMsg = (TextView) findViewById(R.id.tvSoloMsg);
        tvSoloMsg.setText("Image at " + posistion);
        ivSoloPicture = (ImageView) findViewById(R.id.imgSolo);
        // thiết lập hình ảnh đang chọn lên ImageView mới
        ivSoloPicture.setImageResource(mThumbIds[posistion]);
        btnBack = (Button) findViewById(R.id.btnBack);
        // Thiết lập sự kiện click Back để phục hồi lại MainActivity
        // bằng cách gọi lại onCreate(myBackupBundle);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCreate(myBackupBundle);
            }
        });
    }
}
