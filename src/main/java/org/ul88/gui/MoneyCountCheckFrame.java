package org.ul88.gui;

import org.ul88.object.BeverageList;
import org.ul88.object.BeverageObject;
import org.ul88.object.MoneyList;
import org.ul88.object.MoneyObject;
import org.ul88.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MoneyCountCheckFrame extends JFrame {
    public MoneyCountCheckFrame(){
        super("화폐 현황");

        setSize(300,270);
        setLocation(300,100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        MoneyList moneyList = new MoneyList();
        int y = 0;
        for(int i=0;i<moneyList.getList().size();i++){
            MoneyObject moneyObject = moneyList.getList().get(i);
            JLabel label = new JLabel(Integer.toString(moneyObject.getAmount())+"원     "+
                    Integer.toString(moneyObject.getRemaining())+"개");
            label.setFont(new Font("고딕",Font.PLAIN,20));
            label.setBounds(0,y,300,30);
            y+=40;
            add(label);
        }

        JButton button = new JButton("수금");
        button.setBounds(0,y,300,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("수금")){
                    int revenue = 0;
                    for(int i=0;i<moneyList.getList().size();i++){
                        revenue += (moneyList.getList().get(i).getRemaining() - 10)
                                *moneyList.getList().get(i).getAmount();
                        moneyList.getList().get(i).setRemaining(10);
                    }

                    JOptionPane.showMessageDialog(null,"수금한 금액은 "+revenue+"원입니다.");
                    new Repository(moneyList);
                    dispose();
                    new MoneyCountCheckFrame();
                }
            }
        });
        add(button);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new AdminFrame();
            }
        });
    }
}
