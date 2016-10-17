package com.example.bai30_sharepreferences;
 
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    Button btnlogin;
    EditText edituser, editpassword;
    CheckBox chksaveaccount;
    String prefname = "my_data";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin =  (Button) findViewById(R.id.btnlogin);
        edituser = (EditText) findViewById(R.id.editUser);
        editpassword = (EditText) findViewById(R.id.editPassword);
        chksaveaccount = (CheckBox) findViewById(R.id.chksaveacount);
        
        btnlogin.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doLogin();
            }
        });
    }

    protected void doLogin() {
        // TODO Auto-generated method stub
        finish(); // dong man hinh hien tai
        Intent i = new Intent(this, DangNhapThanhCongActivity.class);
        i.putExtra("user", edituser.getText().toString());
        startActivity(i);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        savingPreferences();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        restoringPreferences();
    }
    
    private void savingPreferences() {
        // TODO Auto-generated method stub
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String user = edituser.getText().toString();
        String pwd = editpassword.getText().toString();
        boolean bchk = chksaveaccount.isChecked();
        if (!bchk)
        {
            editor.clear();
        }
        else
        {
            editor.putString("user", user);
            editor.putString("pwd", pwd);
            editor.putBoolean("checked", bchk);
        }
        editor.commit();
    }

    private void restoringPreferences() {
        // TODO Auto-generated method stub
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        boolean bchk = pre.getBoolean("checked", false);
        if (bchk)
        {
            String user = pre.getString("user", "");
            String pwd = pre.getString("pwd", "");
            edituser.setText(user);
            editpassword.setText(pwd);
        }
        chksaveaccount.setChecked(bchk);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
