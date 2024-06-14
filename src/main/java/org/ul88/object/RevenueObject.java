package org.ul88.object;

public class RevenueObject {
    private String date; // 날짜
    private String time; // 시간
    private String AM_PM; // 오전/오후
    private String beverage; // 음료
    private int price; // 가격

    public RevenueObject(String date, String time, String AM_PM, String beverage, int price) {
        this.date = date;
        this.time = time;
        this.AM_PM = AM_PM;
        this.beverage = beverage;
        this.price = price;
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
}
