package org.ul88.repository;

import org.ul88.object.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Repository {
    //beverage.txt, money.txt 모두 갱신
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

    //beverage.txt만 갱신
    public Repository(BeverageList beverageList) {
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
    }

    //money.txt만 갱신
    public Repository(MoneyList moneyList) {
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

    public Repository(RevenueObject revenueObject) {
        //revenueDate.txt 갱신
        try{
            BufferedWriter file = new BufferedWriter(new FileWriter(
                    new File("src/main/resources/TextFile","revenueData.txt"),true
            ));

            file.write(revenueObject.getDate()+" "+
                    revenueObject.getTime()+" "+revenueObject.getAM_PM()+" "
                    +revenueObject.getBeverage()+" "+revenueObject.getPrice()+"\n");

            file.close();
        }catch (IOException ex){
            System.out.println("파일을 불러올 수 없습니다.");
            throw new RuntimeException(ex);
        }
    }

    public Repository(StockObject stockObject) {
        //stockData.txt 갱신
        try{
            BufferedWriter file = new BufferedWriter(new FileWriter(
                    new File("src/main/resources/TextFile","stockData.txt"),true
            ));

            file.write(stockObject.getDate()+" "+
                    stockObject.getTime()+" "+stockObject.getAM_PM()+" "
                    +stockObject.getBeverage()+" "+stockObject.getStock()+"\n");

            file.close();
        }catch (IOException ex){
            System.out.println("파일을 불러올 수 없습니다.");
            throw new RuntimeException(ex);
        }
    }
}
