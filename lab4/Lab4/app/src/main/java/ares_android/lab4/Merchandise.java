package ares_android.lab4;

import java.io.Serializable;

/**
 * Created by 73454 on 2017/10/25.
 */

public class Merchandise implements Serializable {
    private String name;
    private double price;
    private String info;
    private int picname;
    private boolean mark;

    public Merchandise(String name, double price, String info, int picname, boolean mark) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.picname = picname;
        this.mark = mark;
    }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setInfo(String info) { this.info = info; }
    public void  setPicname(int picname) { this.picname = picname; }
    public void setMark (boolean mark) { this.mark = mark; }
    public String getName(){
        return name;
    }
    public double getPrice() { return price; }
    public String getInfo() {
        return info;
    }
    public int getPicname() { return picname; }
    public boolean getMark() { return mark; }
}
