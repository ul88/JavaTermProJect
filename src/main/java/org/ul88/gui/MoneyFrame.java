package org.ul88.gui;

import org.ul88.error.ErrorCode;
import org.ul88.object.MoneyList;
import org.ul88.object.UserObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoneyFrame extends JFrame{
    public MoneyFrame(JPanel jPanel, MoneyList moneyList, UserObject userObject,
                      JLabel nowMoneyLabel){
        ArrayList<JButton> buttonList = new ArrayList<>();

        jPanel.add(nowMoneyLabel);

        int startX = 130, startY = 250;
        int x = startX, y = startY;
        for(int i=0;i<moneyList.getList().size();i++){
            int nowInt = moneyList.getList().get(i).getAmount();
            String now = Integer.toString(nowInt);
            buttonList.add(new JButton(now));
            buttonList.get(i).setBounds(x, y,150,40);


            ActionListener actionListener = (ActionEvent e) ->{
                if(e.getActionCommand() == now){
                    ErrorCode errorCode = userObject.insertMoney(nowInt);
                    if(errorCode == ErrorCode.SUCCESS){
                        nowMoneyLabel.setText(userObject.getMoney()+"원 투입되어있습니다.");
                        System.out.println(now+" "+userObject.getMoney());
                        jPanel.add(nowMoneyLabel);
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
            x+=180;
            if(i%3 == 2){
                x=startX;
                y+=50;
            }
            jPanel.add(buttonList.get(i));
        }
    }
}
