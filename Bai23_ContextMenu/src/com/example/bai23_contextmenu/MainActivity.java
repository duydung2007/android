package com.example.bai23_contextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button1);
        registerForContextMenu(btn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.my_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
        case R.id.itemRed:
            btn.setTextColor(getResources().getColor(R.color.clrred));
            break;
        case R.id.itemGreen:
            btn.setTextColor(getResources().getColor(R.color.clrgreen));
            break;
        case R.id.itemBlue:
            btn.setTextColor(getResources().getColor(R.color.clrblue));
            break;

        default:
            break;
        }
        return super.onContextItemSelected(item);
    }
}
