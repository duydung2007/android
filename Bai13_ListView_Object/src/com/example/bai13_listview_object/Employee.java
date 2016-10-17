package com.example.bai13_listview_object;

public abstract class Employee {
    private String id;
    private String name;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public abstract double TinhLuong();
    
    public String toString()
    {
        return this.id + " - " + this.name;
    }

}
