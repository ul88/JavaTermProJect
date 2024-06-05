package org.ul88.object;

import org.ul88.error.ErrorCode;
import org.ul88.repository.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public ErrorCode insertMoney(MoneyList moneyList,int inputMoney){
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
        for(int i=0;i<moneyList.getList().size();i++){
            if(moneyList.getList().get(i).getAmount() == inputMoney){
                moneyList.getList().get(i).setRemaining(
                        moneyList.getList().get(i).getRemaining()+1
                );
                break;
            }
        }
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

                new Repository(beverageList,moneyList);
                return ErrorCode.SUCCESS;
            }
        }

        return ErrorCode.FAIL;
    }

    //돈을 반환하는 메소드
    public ArrayList<MoneyObject> returnMoney(MoneyList moneyList){
        ArrayList<MoneyObject> moneyCnt = new ArrayList<>();
        for(int i=moneyList.getList().size()-1;i>=0;i--) {
            moneyCnt.add(new MoneyObject(moneyList.getList().get(i).getAmount(), money / moneyList.getList().get(i).getAmount()));
            money %= moneyList.getList().get(i).getAmount();
        }
        moneyCount = 0;
        return moneyCnt;
    }
}
