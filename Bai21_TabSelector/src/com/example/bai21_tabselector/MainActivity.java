package com.example.bai21_tabselector;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    enum Operator {
        Cong, Tru, Nhan, Chia
    }

    Button btnCong, btnTru, btnNhan, btnChia;
    EditText editSoA, editSoB;
    TextView txtKq;
    ListView lvHistory;

    ArrayList<String> arrayOperator = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    OnClickListener myClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
            case R.id.btncong:
                processOperator(Operator.Cong);
                break;
            case R.id.btntru:
                processOperator(Operator.Tru);
                break;
            case R.id.btnnhan:
                processOperator(Operator.Nhan);
                break;
            case R.id.btnchia:
                processOperator(Operator.Chia);
                break;
            default:
                break;
            }
        }

        private void processOperator(Operator operator) {
            // TODO Auto-generated method stub
            String soA = editSoA.getText() + "";
            String soB = editSoB.getText() + "";
            int a = Integer.parseInt(soA);
            int b = Integer.parseInt(soB);
            String kq = "";
            switch (operator) {
            case Cong:
                kq = a + " + " + b + " = " + (a + b);
                break;
            case Tru:
                kq = a + " - " + b + " = " + (a - b);
                break;
            case Nhan:
                kq = a + " * " + b + " = " + (a * b);
                break;
            case Chia:
                if (b != 0)
                    kq = a + " / " + b + " = " + (a * 1.0 / b);
                else
                    kq = "So b phai khac 0";
                break;
            default:
                kq = "Invalid Operator";
                break;
            }
            txtKq.setText(kq);
            arrayOperator.add(kq);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadTabs();
        doFormWidgets();
    }

    private void doFormWidgets() {
        // TODO Auto-generated method stub
        btnCong = (Button) findViewById(R.id.btncong);
        btnTru = (Button) findViewById(R.id.btntru);
        btnNhan = (Button) findViewById(R.id.btnnhan);
        btnChia = (Button) findViewById(R.id.btnchia);
        editSoA = (EditText) findViewById(R.id.editsoa);
        editSoB = (EditText) findViewById(R.id.editsob);
        txtKq = (TextView) findViewById(R.id.txtketqua);
        lvHistory = (ListView) findViewById(R.id.lvhistory);
        btnCong.setOnClickListener(myClick);
        btnTru.setOnClickListener(myClick);
        btnNhan.setOnClickListener(myClick);
        btnChia.setOnClickListener(myClick);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayOperator);
        lvHistory.setAdapter(adapter);
    }

    private void loadTabs() {
        // TODO Auto-generated method stub
        // Lấy tabId ra trước
        final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
        // Gọi lệnh setup
        tab.setup();
        TabHost.TabSpec spec;
        // Tao Tab1
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("1 - Caculator");
        tab.addTab(spec);

        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2 - History");
        tab.addTab(spec);
        // Thiết lập tab mặc định ban đầu là Tab 0
        tab.setCurrentTab(0);
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {
                // TODO Auto-generated method stub
                String s = "Tab tag =" + arg0 + "; index ="
                        + tab.getCurrentTab();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
