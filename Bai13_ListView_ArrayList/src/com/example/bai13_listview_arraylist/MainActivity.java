package com.example.bai13_listview_arraylist;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
    TextView txtChon;
    EditText txtTen;
    Button btn;
    ListView lv;
    ArrayList<String> arrList = null;
    ArrayAdapter<String> adapter = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTen = (EditText) findViewById(R.id.txtTen);
        txtChon = (TextView) findViewById(R.id.txtselection);
        lv = (ListView) findViewById(R.id.lvperson);
        arrList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrList);
        lv.setAdapter(adapter);
        
        btn = (Button) findViewById(R.id.btnNhap);
        btn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                arrList.add(txtTen.getText() + "");
                adapter.notifyDataSetChanged();
            }
        });
        
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                arrList.remove(arg2);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
