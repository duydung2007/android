package com.example.bai19_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView selection;
    GridView gv;
    String arr[] = {"Ipad","Iphone","New Ipad",
            "SamSung","Nokia","Sony Ericson",
            "LG","Q-Mobile","HTC","Blackberry",
            "G Phone","FPT - Phone","HK Phone"
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.textView1);
        gv = (GridView) findViewById(R.id.gridView1);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        gv.setAdapter(adapter);
        
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // TODO Auto-generated method stub
                selection.setText(arr[arg2]);
            }
        });
        
        }
}
