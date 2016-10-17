package com.example.bai26_call_sms;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView txtName, txtPhone;
    private Button btnSave;
    private ListView lvContact;
    private ArrayList<MyContact> arrContact;
    private ArrayAdapter<MyContact> adapterContact;
    
    private MyContact selectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        btnSave.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("Duy Dung", "Button has been clicked ..........");
                addContact();
            }
        });
        
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                selectedContact = arrContact.get(arg2);
                return false;
            }
        });
    }

    protected void addContact() {
        // TODO Auto-generated method stub
        MyContact myContact = new MyContact(txtName.getText() + "", txtPhone.getText() + "");
        arrContact.add(myContact);
        adapterContact.notifyDataSetChanged();
    }

    private void getWidget() {
        // TODO Auto-generated method stub
        txtName = (TextView) findViewById(R.id.editName);
        txtPhone = (TextView) findViewById(R.id.editPhone);
        btnSave = (Button) findViewById(R.id.btnSaveContact);
        lvContact = (ListView) findViewById(R.id.lvContact);
        arrContact = new ArrayList<MyContact>();
        adapterContact = new ArrayAdapter<MyContact>(this, android.R.layout.simple_list_item_1, arrContact);
        lvContact.setAdapter(adapterContact);
        registerForContextMenu(lvContact);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
        case R.id.menuCall:
            makeCall();
            break;
        case R.id.menuSMS:
            sendSMS();
            break;
        case R.id.menuRemove:
            removeItem();
            break;
        default:
            break;
        }
        return super.onContextItemSelected(item);
    }

    private void removeItem() {
        // TODO Auto-generated method stub
        arrContact.remove(selectedContact);
        adapterContact.notifyDataSetChanged();
    }

    private void sendSMS() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this, MySMSActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("CONTACT", selectedContact);
        i.putExtra("DATA", b);
        startActivity(i);
    }

    private void makeCall() {
        // TODO Auto-generated method stub
        Uri uri = Uri.parse("tel:" + selectedContact.getPhone());
        Intent i = new Intent(Intent.ACTION_CALL, uri);
        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.phonecontextmenu, menu);
        menu.setHeaderTitle("Call - SMS");
        menu.getItem(0).setTitle("Call to " + selectedContact.getName());
        menu.getItem(1).setTitle("Send SMS to " + selectedContact.getPhone());
    }
    
    
}
