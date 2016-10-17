package com.example.bai24_intent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    public static final int REQUEST_CODE_INPUT = 113;
    public static final int RESULT_CODE_SAVE1 = 114;
    public static final int RESULT_CODE_SAVE2 = 115;
    Button btnInputData;
    ListView lvData;
    ArrayList<Integer> arrData = new ArrayList<Integer>();
    ArrayAdapter<Integer> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInputData = (Button) findViewById(R.id.btnopenactivity);
        btnInputData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this,
                        InputDataActivity.class);
                startActivityForResult(intent, REQUEST_CODE_INPUT);
            }
        });

        lvData = (ListView) findViewById(R.id.lvdata);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                arrData);
        lvData.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INPUT) {
            switch (resultCode) {
            case RESULT_CODE_SAVE1:
                int v1 = data.getIntExtra("data", 0);
                arrData.add(v1 * v1);
                adapter.notifyDataSetChanged();
                break;
            case RESULT_CODE_SAVE2:
                int v2 = data.getIntExtra("data", 0);
                arrData.add(v2);
                adapter.notifyDataSetChanged();;
                break;

            default:
                break;
            }
        }
    }

}
