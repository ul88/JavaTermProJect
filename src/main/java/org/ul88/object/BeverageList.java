package org.ul88.object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BeverageList {
    // 음료 리스트
    private ArrayList<BeverageObject> list = new ArrayList<>();

    public BeverageList() {
        // 음료 리스트 파일을 읽어와서 리스트에 추가
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
            System.out.println("파일을 불러올 수 없습니다.");
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
