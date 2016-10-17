package com.example.bai30_sharepreferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DangNhapThanhCongActivity extends Activity {

    TextView txtMsg;
    Button btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_thanh_cong);
        txtMsg = (TextView) findViewById(R.id.txtmsg);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        
        Intent i = getIntent();
        txtMsg.setText("Hello: " + i.getStringExtra("user"));
    }
}
