package org.ul88.object;

import org.ul88.error.ErrorCode;
import org.ul88.repository.Repository;

import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UserObject {
    private String beverage;
    private int money;
    private int moneyCount; //지폐 개수

    public UserObject(String beverage, int money) {
        this.beverage = beverage;
        this.money = money;
        moneyCount = 0;
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

    //자판기에 금액을 투입하는 메소드
    public ErrorCode insertMoney(MoneyList moneyList,int inputMoney){

        if(inputMoney == 1000 && moneyCount == 5){
            return ErrorCode.FAIL_MANY_PAPER;
        }
        if(money + inputMoney > 7000){
            return ErrorCode.FAIL_MAX_MONEY;
        }

        if(inputMoney == 1000) moneyCount++;

        money += inputMoney;

        for(int i=0;i<moneyList.getList().size();i++){
            if(moneyList.getList().get(i).getAmount() == inputMoney){
                moneyList.getList().get(i).setRemaining(
                        moneyList.getList().get(i).getRemaining()+1
                );
                break;
            }
        }

        new Repository(moneyList);
        return ErrorCode.SUCCESS;
    }

    //음료를 구매하는 메소드
    public ErrorCode buyDrink(BeverageList beverageList, MoneyList moneyList){
        System.out.println("구매 매소드 실행");
        for(BeverageObject beverageObject : beverageList.getList()){
            if(beverageObject.getName().equals(beverage)){
                if(beverageObject.getRemaining() <= 0 ) return ErrorCode.FAIL_SOLD_OUT;
                if(beverageObject.getPrice() > money) return ErrorCode.FAIL_NO_MONEY;
                System.out.println("구매 성공");
                beverageObject.setRemaining(beverageObject.getRemaining()-1);

                money = money - beverageObject.getPrice();
                if(moneyCount != 0) moneyCount--;
                //날짜 기록
                Date nowDate = new Date();
                RevenueObject revenueObject = new RevenueObject(
                        new SimpleDateFormat("yyyy-MM-dd").format(nowDate),
                        new SimpleDateFormat("HH:mm:ss").format(nowDate),
                        new SimpleDateFormat("a").format(nowDate),
                        beverageObject.getName(),
                        beverageObject.getPrice()
                );

                new Repository(beverageList,moneyList);
                new Repository(revenueObject);
                return ErrorCode.SUCCESS;
            }
        }

        return ErrorCode.FAIL;
    }

    //돈을 반환하는 메소드
    public ArrayList<MoneyObject> returnMoney(MoneyList moneyList){
        ArrayList<MoneyObject> moneyCnt = new ArrayList<>();

        int money = this.money;
        for(int i=moneyList.getList().size()-1;i>=0;i--){
            MoneyObject moneyObject = moneyList.getList().get(i);
            moneyCnt.add(new MoneyObject(moneyObject.getAmount(),
                    money/moneyObject.getAmount())
            );
            moneyList.getList().get(i).setRemaining(
                    moneyObject.getRemaining() - money/moneyObject.getAmount()
            );
            money %= moneyObject.getAmount();
        }
        this.money = 0;
        moneyCount = 0;
        //data파일에 갱신
        new Repository(moneyList);
        return moneyCnt;
    }
}
