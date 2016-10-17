package com.example.bai22_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.mymenu, menu);
        int itemId = 113;
        menu.add(0, itemId, 0, "Menu 1");
        itemId = 114;
        menu.add(0, itemId, 0, "Menu 2");
        itemId = 115;
        SubMenu sub3 = menu.addSubMenu("MN 3");
        sub3.add(0, itemId, 0, "Sub 1");
        itemId = 116;
        sub3.add(0, itemId, 0, "Sub 2");
        itemId = 117;
        sub3.add(0, itemId, 0, "Sub 3");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
        case R.id.item_xemdssv:
            
            break;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    

}
