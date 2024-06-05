package org.ul88.object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BeverageList {
    private ArrayList<BeverageObject> list = new ArrayList<>();

    public BeverageList() {
        try {
            BufferedReader file = new BufferedReader(new FileReader(
                    new File("src/main/resources/TextFile","beverage.txt")
            ));

            String str="";
            while((str = file.readLine()) != null){
                String[] beverageStr = str.split(" ");

                list.add(new BeverageObject(beverageStr[0],Integer.parseInt(beverageStr[1]),
                        Integer.parseInt(beverageStr[2]))
                );
            }
            file.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<BeverageObject> getList() {
        return list;
    }

    public void setList(ArrayList<BeverageObject> list) {
        this.list = list;
    }
}
