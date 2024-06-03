package org.ul88;

import org.ul88.error.ErrorCode;
import org.ul88.object.*;

import java.util.ArrayList;

public class Buy {
    //음료 구매 메소드
    //음료 구매 결과에 따라 오류메시지를 다르게 출력해야하므로 enum형을 가지는 ErrorCode class를 return 해줌
    public ErrorCode buyDrink(BeverageList beverageList, UserObject userObject){

        System.out.println("구매 매소드 실행");
        for(BeverageObject beverageObject : beverageList.getList()){
            if(beverageObject.getName().equals(userObject.getBeverage())){
                if(beverageObject.getRemaining() <= 0 ) return ErrorCode.FAIL_SOLD_OUT;
                if(beverageObject.getPrice() > userObject.getMoney()) return ErrorCode.FAIL_NO_MONEY;
                System.out.println("구매 성공");
                beverageObject.setRemaining(beverageObject.getRemaining()-1);

                userObject.setMoney(userObject.getMoney() - beverageObject.getPrice());
                if(userObject.getMoneyCount() != 0) userObject.setMoneyCount(userObject.getMoneyCount()-1);
                return ErrorCode.SUCCESS;
            }
        }

        return ErrorCode.FAIL;
    }

    //투입한 돈 반환 메소드
    public ArrayList<MoneyObject> returnMoney(MoneyList moneyList, UserObject userObject){
        int money = userObject.getMoney();
        ArrayList<MoneyObject> moneyCnt = new ArrayList<>();
        for(int i=moneyList.getList().size()-1;i>=0;i--) {
            moneyCnt.add(new MoneyObject(moneyList.getList().get(i).getAmount(), money / moneyList.getList().get(i).getAmount()));
            money %= moneyList.getList().get(i).getAmount();
        }
        userObject.setMoney(0);
        userObject.setMoneyCount(0);
        return moneyCnt;
    }
}
