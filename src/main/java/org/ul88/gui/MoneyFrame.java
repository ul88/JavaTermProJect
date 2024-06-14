package org.ul88.gui;

import org.ul88.error.ErrorCode;
import org.ul88.object.BeverageList;
import org.ul88.object.MoneyList;
import org.ul88.object.UserObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoneyFrame extends JFrame{
    public MoneyFrame(JPanel jPanel, BeverageList beverageList,
                      MoneyList moneyList, UserObject userObject,
                      JLabel nowMoneyLabel, ArrayList<JButton> beverageButtonList){
        ArrayList<JButton> buttonList = new ArrayList<>();

        jPanel.add(nowMoneyLabel);

        int startX = 37, startY = 680;
        int x = startX, y = startY;
        // for문을 이용하여 돈 버튼을 일정한 간격으로 추가한다.
        for(int i=0;i<moneyList.getList().size();i++){
            int nowInt = moneyList.getList().get(i).getAmount();
            String now = Integer.toString(nowInt);
            buttonList.add(new JButton(now+"원"));
            buttonList.get(i).setBounds(x, y,80,40);

            ActionListener actionListener = (ActionEvent e) ->{
                if(e.getActionCommand().equals(now+"원")){
                    ErrorCode errorCode = userObject.insertMoney(moneyList,nowInt);
                    if(errorCode == ErrorCode.SUCCESS){
                        nowMoneyLabel.setText(userObject.getMoney()+"원");

                        for(int j=0;j<beverageList.getList().size();j++){
                            if(beverageList.getList().get(j).getName().equals("empty")) continue;
                            if(beverageList.getList().get(j).getRemaining() == 0){
                                beverageButtonList.get(j).setBackground(Color.GRAY);
                                continue;
                            }
                            if(beverageList.getList().get(j).getPrice() <= userObject.getMoney()){
                                beverageButtonList.get(j).setBackground(Color.GREEN);
                            }else{
                                beverageButtonList.get(j).setBackground(Color.RED);
                            }
                        }

                        System.out.println(now+" "+userObject.getMoney());
                    }else{
                        if(errorCode == ErrorCode.FAIL_MAX_MONEY){
                            JOptionPane.showMessageDialog(null,"투입 가능한 금액을 넘어갔습니다.");
                        }
                        else if(errorCode == ErrorCode.FAIL_MANY_PAPER){
                            JOptionPane.showMessageDialog(null,"지폐는 5장까지만 투입이 가능합니다.\n" +
                                    "반환 후 다시 투입해주세요.");
                        }
                    }
                }
            };
            buttonList.get(i).addActionListener(actionListener);
            x+=110;
            jPanel.add(buttonList.get(i));
        }
    }
}
