package com.example.bai26_call_sms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MySMSActivity extends Activity {
    private Button btnSend;
    private EditText editSMS;
    private TextView txtSendTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sms);
        btnSend = (Button) findViewById(R.id.btnSendSms);
        editSMS = (EditText) findViewById(R.id.editSMS);
        txtSendTo = (EditText) findViewById(R.id.txtSendTo);
        // get Intent information
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        final MyContact myContact = (MyContact) b.getSerializable("CONTACT");

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendSMS(myContact);
            }
        });
        txtSendTo.setText("Send SMS to " + myContact.getPhone());
    }

    protected void sendSMS(MyContact myContact) {
        // TODO Auto-generated method stub
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        // Khai bao PendingIntent de kiem tra ket qua
        final PendingIntent pendingMsgSent = PendingIntent.getBroadcast(this,
                0, msgSent, 0);
        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                int result = getResultCode();
                String msg = "Send OK";
                if (result != Activity.RESULT_OK) {
                    msg = "Send failed";
                }
                Toast.makeText(MySMSActivity.this, msg, Toast.LENGTH_LONG)
                        .show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));

        sms.sendTextMessage(myContact.getPhone(), null, editSMS.getText() + "",
                pendingMsgSent, null);
        finish();
    }
}
