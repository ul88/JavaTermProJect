package org.ul88.gui;

import org.ul88.Buy;
import org.ul88.error.ErrorCode;
import org.ul88.object.BeverageList;
import org.ul88.object.MoneyList;
import org.ul88.object.MoneyObject;
import org.ul88.object.UserObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class frame extends JFrame
{
    private UserObject userObject = new UserObject("", 0, 0);
    private JLabel nowMoneyLabel;
    private final Buy buy = new Buy();

    public frame(BeverageList beverageList, MoneyList moneyList) {
        super("자판기 프로그램");

        setSize(800,800);
        setLocation(100, 100);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        nowMoneyLabel = new JLabel(userObject.getMoney()+"원 투입되어있습니다.");
        nowMoneyLabel.setBounds(300,350,500,100);
        nowMoneyLabel.setFont(nowMoneyLabel.getFont().deriveFont(26.0f));

        new BeverageFrame(jPanel,beverageList, moneyList,userObject,nowMoneyLabel);
        new MoneyFrame(jPanel,moneyList,userObject,nowMoneyLabel);
        makeReturnButton(jPanel,beverageList,moneyList);

        add(jPanel);

        setVisible(true);
    }

    public void makeReturnButton(JPanel jPanel,BeverageList beverageList,MoneyList moneyList){
        JButton returnMoneyButton = new JButton("반환");
        returnMoneyButton.setBounds(310,450,150,40);

        ActionListener actionListener = (ActionEvent e) ->{
            if(e.getActionCommand().equals("반환")){
                ArrayList<MoneyObject> returnMoneyList = buy.returnMoney(moneyList,userObject);
                String message = "";
                for(int i=0;i<returnMoneyList.size();i++){
                    message += Integer.toString(returnMoneyList.get(i).getAmount())+"원을 ";
                    message += Integer.toString(returnMoneyList.get(i).getRemaining());
                    message += "개 반환했습니다.\n";
                }
                JOptionPane.showMessageDialog(null,message);
                nowMoneyLabel.setText(userObject.getMoney()+"원 투입되어있습니다.");
                jPanel.add(nowMoneyLabel);
            }
        };

        returnMoneyButton.addActionListener(actionListener);
        jPanel.add(returnMoneyButton);
    }
}