package com.example.bai24_intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputDataActivity extends Activity {
    Button btnSave1, btnSave2;
    EditText editNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        btnSave1 = (Button) findViewById(R.id.btnSave1);
        btnSave2 = (Button) findViewById(R.id.btnSave2);
        editNumber = (EditText) findViewById(R.id.editNumber);
        btnSave1.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendToMain(MainActivity.RESULT_CODE_SAVE1);
            }
        });
        
        btnSave2.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendToMain(MainActivity.RESULT_CODE_SAVE2);
            }
        });
    }
    
    
    protected void sendToMain(int resultCode) {
        // TODO Auto-generated method stub
        Intent intent = getIntent();
        int value = Integer.parseInt(editNumber.getText() + "");
        intent.putExtra("data", value);
        setResult(resultCode, intent);
        finish();
    }

}
