package com.example.bai13_listview_object;

public class EmployeeFullTime extends Employee {

    @Override
    public double TinhLuong() {
        // TODO Auto-generated method stub
        return 500;
    }
    
    public String toString()
    {
        return super.toString() + "--> FullTime= " + TinhLuong();
    }

}
