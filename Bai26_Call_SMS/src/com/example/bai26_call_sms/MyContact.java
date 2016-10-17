package com.example.bai26_call_sms;

import java.io.Serializable;

public class MyContact implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String phone;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public MyContact(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }
    public MyContact() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.name+"["+this.phone+"]";
    }
}
