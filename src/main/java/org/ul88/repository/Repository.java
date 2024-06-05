package org.ul88.repository;

import org.ul88.object.BeverageList;
import org.ul88.object.BeverageObject;
import org.ul88.object.MoneyList;
import org.ul88.object.MoneyObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Repository {
    public Repository(BeverageList beverageList, MoneyList moneyList) {
        //beverage.txt 갱신
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(
                    new File("src/main/resources/TextFile","beverage.txt")
            ));


            for(int i=0;i<beverageList.getList().size();i++){
                BeverageObject beverageObject = beverageList.getList().get(i);
                file.write(beverageObject.getName()+" "+
                        beverageObject.getPrice()+" "+
                        beverageObject.getRemaining()+"\n");
            }

            file.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //money.txt 갱신
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(
                    new File("src/main/resources/TextFile","money.txt")
            ));


            for(int i=0;i<moneyList.getList().size();i++){
                MoneyObject moneyObject = moneyList.getList().get(i);
                file.write(moneyObject.getAmount()+" "+
                        moneyObject.getRemaining()+"\n");
            }

            file.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
