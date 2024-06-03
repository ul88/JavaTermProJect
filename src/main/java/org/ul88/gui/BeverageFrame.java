package org.ul88.gui;

import org.ul88.Buy;
import org.ul88.error.ErrorCode;
import org.ul88.object.BeverageList;
import org.ul88.object.MoneyList;
import org.ul88.object.UserObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BeverageFrame extends JFrame{
    private final Buy buy = new Buy();

    public BeverageFrame(JPanel jPanel, BeverageList beverageList, MoneyList moneyList,
                         UserObject userObject, JLabel nowMoneyLabel) {
        ArrayList<JButton> buttonList = new ArrayList<>();

        int startX = 130, startY = 40;
        int x = startX, y = startY;
        for(int i=0;i<beverageList.getList().size();i++){
            String now = beverageList.getList().get(i).getName();
            buttonList.add(new JButton(now));
            buttonList.get(i).setBounds(x, y,150,40);

            ActionListener actionListener = (ActionEvent e) ->{
                if(e.getActionCommand().equals(now)){
                    userObject.setBeverage(now);
                    System.out.println(now);

                    System.out.println("구매 시작");
                    ErrorCode errorCode = buy.buyDrink(beverageList,userObject);

                    if(errorCode == ErrorCode.SUCCESS){
                        JOptionPane.showMessageDialog(null, now+" 구매에 성공했습니다.");
                    }else if(errorCode == ErrorCode.FAIL_NO_MONEY){
                        JOptionPane.showMessageDialog(null, "구매에 실패하였습니다.\n" +
                                "금액을 더 많이 투입해주세요.");
                    }else if(errorCode == ErrorCode.FAIL_SOLD_OUT){
                        JOptionPane.showMessageDialog(null, "구매에 실패하였습니다.\n" +
                                "재고가 부족합니다.\n다른 상품을 구매해주세요.");
                    }else{
                        System.out.println("ERROR 오류 발생 : 잘못된 음료 이름을 가지고 있습니다.");
                    }

                    nowMoneyLabel.setText(userObject.getMoney()+"원 투입되어있습니다.");
                    jPanel.add(nowMoneyLabel);
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
