package org.ul88;

import org.ul88.gui.frame;
import org.ul88.object.BeverageList;
import org.ul88.object.BeverageObject;
import org.ul88.object.MoneyList;
import org.ul88.object.MoneyObject;

public class Main {
    public static void main(String[] args)   {
        BeverageList beverageList = new BeverageList();
        MoneyList moneyList = new MoneyList();

        beverageList.translation(1);

        new frame(beverageList, moneyList);

        for(BeverageObject iter : beverageList.getList()){
            System.out.println(iter.getName()+" "+iter.getRemaining()+" "+iter.getPrice());
        }

        for(MoneyObject iter : moneyList.getList()){
            System.out.println(iter.getAmount()+" "+iter.getRemaining());
        }
    }
}