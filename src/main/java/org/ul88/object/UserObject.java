package org.ul88.object;

import org.ul88.error.ErrorCode;
import org.ul88.repository.Repository;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserObject {
    private String beverage;
    private int money;
    private int moneyCount; //지폐 개수

    public UserObject() {
        beverage = "";
        money = 0;
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
        //투입한 금액이 천원이면 지폐이므로 지폐 개수가 5인지 확인한다.
        if(inputMoney == 1000 && moneyCount == 5){
            return ErrorCode.FAIL_MANY_PAPER; // 구매가 불가능하므로 에러코드 반환
        }
        if(money + inputMoney > 7000){ //투입한 금액이 7000원을 넘어가면 에러코드 반환
            return ErrorCode.FAIL_MAX_MONEY;
        }

        if(inputMoney == 1000) moneyCount++; // 투입한 금액이 천원이면 지페이므로 지폐 개수를 하나 증가시킨다.

        money += inputMoney; // 투입한 금액을 총 금액에 추가한다.

        // 투입한 금액을 moneyList에서 찾은 후 해당 금액의 남은 개수를 1 증가시킨다.
        for(int i=0;i<moneyList.getList().size();i++){
            if(moneyList.getList().get(i).getAmount() == inputMoney){
                moneyList.getList().get(i).setRemaining(
                        moneyList.getList().get(i).getRemaining()+1
                );
                break;
            }
        }

        //Reposiotry 클래스에서 moneyList를 money.txt 파일에 갱신한다.
        new Repository(moneyList);
        return ErrorCode.SUCCESS; // 오류 없이 성공적으로 투입했으므로 SUCCESS 반환
    }

    //음료를 구매하는 메소드
    public ErrorCode buyDrink(BeverageList beverageList, MoneyList moneyList){
        System.out.println("구매 매소드 실행");
        // 구매한 음료와 같은 이름을 가진 음료를 찾아서 구매한다.
        for(BeverageObject beverageObject : beverageList.getList()){
            if(beverageObject.getName().equals(beverage)){
                // 해당 음료의 개수가 0보다 작거나 같다면, 음료가 없는 것이므로 에러코드 반환
                if(beverageObject.getRemaining() <= 0 ) return ErrorCode.FAIL_SOLD_OUT;
                // 해당 음료의 가격이 투입된 돈보다 크다면, 돈이 부족한 것이므로 에러코드 반환
                if(beverageObject.getPrice() > money) return ErrorCode.FAIL_NO_MONEY;
                System.out.println("구매 성공");

                // 음료 구매에 성공했으므로 음료의 개수를 1감소 시킨다.
                beverageObject.setRemaining(beverageObject.getRemaining()-1);

                //투입된 돈의 금액도 음료의 가격만큼 감소시킨다.
                money = money - beverageObject.getPrice();
                //투입된 지폐의 개수가 0개가 아니면 지폐 개수를 1 감소시킨다.
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

                // 구매한 음료의 개수가 0이 되면 품절로 표시하고 날짜를 다시 한번 기록한다.
                if(beverageObject.getRemaining() == 0) {
                    StockObject stockObject = new StockObject(
                            new SimpleDateFormat("yyyy-MM-dd").format(nowDate),
                            new SimpleDateFormat("HH:mm:ss").format(nowDate),
                            new SimpleDateFormat("a").format(nowDate),
                            beverageObject.getName(),
                            "품절"
                    );

                    //stockData.txt를 갱신한다.
                    new Repository(stockObject);
                }

                //beverage.txt, money.txt, revenueData.txt를 갱신한다.
                new Repository(beverageList);
                new Repository(moneyList);
                new Repository(revenueObject);

                //구매에 성공했으므로 SUCCESS를 반환한다.
                return ErrorCode.SUCCESS;
            }
        }

        //만약 실패했다면 FAIL을 반환한다.
        return ErrorCode.FAIL;
    }

    //돈을 반환하는 메소드
    public ArrayList<MoneyObject> returnMoney(MoneyList moneyList){
        //반환할 금액을 담을 ArrayList를 생성한다.
        ArrayList<MoneyObject> moneyCnt = new ArrayList<>();

        int money = this.money;

        //가장 적은 개수의 돈을 반환하기 위해선 1000원부터 확인해야하므로 거꾸로 반복문을 탐색한다.
        for(int i=moneyList.getList().size()-1;i>=0;i--){
            MoneyObject moneyObject = moneyList.getList().get(i);
            moneyCnt.add(new MoneyObject(moneyObject.getAmount(),
                    money/moneyObject.getAmount())
            );
            if(moneyObject.getRemaining() - money/moneyObject.getAmount() < 0){
                continue;
            }
            moneyList.getList().get(i).setRemaining(
                    moneyObject.getRemaining() - money/moneyObject.getAmount()
            );
            money %= moneyObject.getAmount();
        }
        //반환에 성공하지 못했다면 오류를 빈 ArrayList를 반환한다.
        if(money != 0){
            JOptionPane.showMessageDialog(null,"거스름 돈이 부족하여 반환이 불가능합니다.\n" +
                    "관리자에게 문의해주세요.");
            return new ArrayList<MoneyObject>();
        }
        //성공했다면 값을 전부 0으로 초기화한다.
        this.money = 0;
        moneyCount = 0;
        //money.txt 파일 갱신
        new Repository(moneyList);
        
        // moneyCnt 반환
        return moneyCnt;
    }
}
