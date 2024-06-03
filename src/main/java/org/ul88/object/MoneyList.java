package org.ul88.object;

import java.util.ArrayList;

public class MoneyList {
    ArrayList<MoneyObject> list = new ArrayList<>();

    public MoneyList(){
        list.add(new MoneyObject(10,10));
        list.add(new MoneyObject(50,10));
        list.add(new MoneyObject(100,10));
        list.add(new MoneyObject(500,10));
        list.add(new MoneyObject(1000,10));
    }

    public ArrayList<MoneyObject> getList() {
        return list;
    }

    public void setList(ArrayList<MoneyObject> list) {
        this.list = list;
    }


}
