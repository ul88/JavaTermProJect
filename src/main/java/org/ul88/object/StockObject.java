package org.ul88.object;

public class StockObject {
    private String date; // 날짜
    private String time; // 시간
    private String AM_PM; // 오전/오후
    private String beverage; // 음료
    private String stock; // 재고

    public StockObject(String date, String time, String AM_PM, String beverage, String stock) {
        this.date = date;
        this.time = time;
        this.AM_PM = AM_PM;
        this.beverage = beverage;
        this.stock = stock;
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

    public String getStock() {
        return stock;
    }
}
