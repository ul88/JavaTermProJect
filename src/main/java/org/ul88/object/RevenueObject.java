package org.ul88.object;

import java.util.ArrayList;
import java.util.Date;

public class RevenueObject {
    private String date;
    private String time;
    private String AM_PM;
    private String beverage;
    private int price;

    public RevenueObject(String date, String time, String AM_PM, String beverage, int price) {
        this.date = date;
        this.time = time;
        this.AM_PM = AM_PM;
        this.beverage = beverage;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAM_PM() {
        return AM_PM;
    }

    public String getBeverage() {
        return beverage;
    }

    public int getPrice() {
        return price;
    }

    public String year(){
        return date.split("-")[0];
    }

    public String month() {
        return date.split("-")[1];
    }

    public String day() {
        return date.split("-")[2];
    }
}
