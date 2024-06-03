package org.ul88.object;

import org.ul88.error.ErrorCode;

public class UserObject {
    private String beverage;
    private int money;
    private int moneyCount;

    public UserObject(String beverage, int money, int moneyCount) {
        this.beverage = beverage;
        this.money = money;
        this.moneyCount = moneyCount;
    }

    public String getBeverage() {
        return beverage;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    public ErrorCode insertMoney(int inputMoney){
        if(inputMoney == 1000){
            moneyCount++;
        }
        if(moneyCount > 5){
            moneyCount--;
            return ErrorCode.FAIL_MANY_PAPER;
        }

        money += inputMoney;

        if(money > 7000){
            money -= inputMoney;
            return ErrorCode.FAIL_MAX_MONEY;
        }
        return ErrorCode.SUCCESS;
    }
}
