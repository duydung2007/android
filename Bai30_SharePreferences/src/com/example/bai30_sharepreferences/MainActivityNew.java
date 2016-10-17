package com.example.bai30_sharepreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivityNew extends Activity
            implements OnSharedPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnlogin);
        btn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivityNew.this,
                        MySettingActivity.class);
                startActivityForResult(intent, 113);
            }
        });
        
        Context ctx = getApplicationContext();
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(ctx);
        pre.registerOnSharedPreferenceChangeListener(this);
    }
    
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // TODO Auto-generated method stub
        boolean data = sharedPreferences.getBoolean(key, false);
        Toast.makeText(this, "Checked= " + data, Toast.LENGTH_LONG).show();
    }

}
