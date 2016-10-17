package com.example.bai13_listview_object;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
    EditText editId, editName;
    Button btnNhap;
    ListView lvNhanVien;
    RadioGroup radGroup;
    ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
    ArrayAdapter<Employee> adapter = null;
    
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId = (EditText) findViewById(R.id.editMa);
        editName = (EditText) findViewById(R.id.editTen);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        radGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        lvNhanVien = (ListView) findViewById(R.id.lvNhanVien);
        
        adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, arrEmployee);
        lvNhanVien.setAdapter(adapter);
        
        btnNhap.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                processNhap();
            }
        });
        
    }
    
    
    private void processNhap()
    {
        int radId = radGroup.getCheckedRadioButtonId();
        String id = editId.getText() + "";
        String ten = editName.getText() + "";
        if (radId == R.id.radioChinhthuc)
        {
            employee = new EmployeeFullTime();
        }
        else
        {
            employee = new EmployeePartTime();
        }
        employee.setId(id);
        employee.setName(ten);
        arrEmployee.add(employee);
        adapter.notifyDataSetChanged();
    }
}
