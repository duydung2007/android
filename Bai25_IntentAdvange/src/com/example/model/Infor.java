package com.example.model;

import java.io.Serializable;

public class Infor implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String ma;
    String ten;
    public Infor(String ma, String ten) {
        super();
        this.ma = ma;
        this.ten = ten;
    }
    public Infor() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getMa() {
        return ma;
    }
    public void setMa(String ma) {
        this.ma = ma;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.ma + " - " + this.ten;
    }
}
