package org.ul88.object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MoneyList {
    ArrayList<MoneyObject> list = new ArrayList<>();

    public MoneyList(){
        try {
            BufferedReader file = new BufferedReader(new FileReader(
                    new File("src/main/resources/TextFile","money.txt")
            ));

            String str="";
            while((str = file.readLine()) != null){
                String[] moneyStr = str.split(" ");

                list.add(new MoneyObject(Integer.parseInt(moneyStr[0]),
                        Integer.parseInt(moneyStr[1]))
                );
            }
            file.close();
        } catch (IOException ex) {
            System.out.println("파일을 불러올 수 없습니다.");
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<MoneyObject> getList() {
        return list;
    }

    public void setList(ArrayList<MoneyObject> list) {
        this.list = list;
    }
}
