package org.ul88.gui;

import org.ul88.object.BeverageList;
import org.ul88.object.BeverageObject;
import org.ul88.object.MoneyList;
import org.ul88.object.MoneyObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MoneyCountCheckFrame extends JFrame {
    public MoneyCountCheckFrame(){
        super("화폐 현황");

        setSize(300,250);
        setLocation(300,100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        MoneyList moneyList = new MoneyList();
        int startY = 0;
        for(int i=0;i<moneyList.getList().size();i++){
            MoneyObject moneyObject = moneyList.getList().get(i);
            JLabel label = new JLabel(Integer.toString(moneyObject.getAmount())+"원     "+
                    Integer.toString(moneyObject.getRemaining())+"개");
            label.setFont(new Font("고딕",Font.PLAIN,20));
            label.setBounds(0,startY,300,30);
            startY+=40;
            add(label);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new AdminFrame();
            }
        });
    }
}
