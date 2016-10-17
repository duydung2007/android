package com.example.bai30_sharepreferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MySettingActivity extends PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypreferencelayout);
    }
    
}
