package org.ul88.object;

import java.util.ArrayList;
import java.util.HashMap;

public class BeverageList {
    private ArrayList<BeverageObject> list = new ArrayList<>();
    private HashMap<String, String> engToKor = new HashMap<>();
    private HashMap<String, String> korToEng = new HashMap<>();

    public BeverageList() {
        list.add(new BeverageObject("water",450,10));
        list.add(new BeverageObject("coffee",500,10));
        list.add(new BeverageObject("ionic drink",550,10));
        list.add(new BeverageObject("premium coffee",700,10));
        list.add(new BeverageObject("pop drink",750,10));
        list.add(new BeverageObject("special drink",800,10));

        engToKor.put("water","물");
        engToKor.put("coffee","커피");
        engToKor.put("ionic drink","이온음료");
        engToKor.put("premium coffee","고급커피");
        engToKor.put("pop drink","탄산음료");
        engToKor.put("special drink","특화음료");

        korToEng.put("물","water");
        korToEng.put("커피","coffee");
        korToEng.put("이온음료","ionic drink");
        korToEng.put("고급커피","premium doffee");
        korToEng.put("탄산음료","pop drink");
        korToEng.put("특화음료","special drink");
    }

    public ArrayList<BeverageObject> getList() {
        return list;
    }

    public void setList(ArrayList<BeverageObject> list) {
        this.list = list;
    }

    public void translation(int language){
        if(language == 1){ // 영어 -> 한국어
            for(int i=0;i<list.size();i++){
                String s = list.get(i).getName();
                list.get(i).setName(engToKor.get(s));
            }
        }else if(language == 2){ // 한국어 -> 영어
            for(int i=0;i<list.size();i++){
                String s = list.get(i).getName();
                list.get(i).setName(korToEng.get(s));
            }
        }
    }
}
