package com.example.bai13_listview_object;

public class EmployeePartTime extends Employee {

    @Override
    public double TinhLuong() {
        // TODO Auto-generated method stub
        return 150;
    }
    
    public String toString()
    {
        return super.toString() + "--> PartTime: " + TinhLuong();
    }

}
