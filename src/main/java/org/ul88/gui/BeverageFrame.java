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

public class BeverageFrame extends JFrame{
    public BeverageFrame(JPanel jPanel, BeverageList beverageList, MoneyList moneyList,
                         UserObject userObject, JLabel nowMoneyLabel) {
        ArrayList<JButton> buttonList = new ArrayList<>();

        ArrayList<ImageIcon> imageList = init();

        int startX = 167, startY = 188;
        int x = startX, y = startY;
        for(int i=0;i<beverageList.getList().size();i++){
            String now = beverageList.getList().get(i).getName();
            buttonList.add(new JButton(now));
            buttonList.get(i).setBounds(x, y,30,20);
            buttonList.get(i).setBackground(Color.BLACK);
            ActionListener actionListener = (ActionEvent e) ->{
                if(e.getActionCommand().equals(now)){
                    userObject.setBeverage(now);
                    System.out.println(now);

                    System.out.println("구매 시작");
                    ErrorCode errorCode = userObject.buyDrink(beverageList,moneyList);

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

                    nowMoneyLabel.setText(userObject.getMoney()+"원");
                }
            };
            buttonList.get(i).addActionListener(actionListener);
            x+=47;
            if(i%6 == 5){
                x=startX;
                y+=84;
            }
            jPanel.add(buttonList.get(i));
        }

        startX = 160;
        startY = 133;
        x = startX;
        y = startY;
        for(int i=0;i<imageList.size();i++){
            JLabel label = new JLabel(imageList.get(i));
            label.setBounds(x,y,50,50);
            x+=45;
            if(i%6 == 5){
                x=startX;
                y+=84;
            }
            jPanel.add(label);
        }
    }

    public ArrayList<ImageIcon> init(){
        ArrayList<ImageIcon> imageList = new ArrayList<>();

        int width = 60, height = 50;
        Image tempImg = new ImageIcon("src/images/물.png").getImage();
        tempImg = tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageList.add(new ImageIcon(tempImg));

        tempImg = new ImageIcon("src/images/레쓰비.png").getImage();
        tempImg = tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageList.add(new ImageIcon(tempImg));

        tempImg = new ImageIcon("src/images/포카리.png").getImage();
        tempImg = tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageList.add(new ImageIcon(tempImg));

        tempImg = new ImageIcon("src/images/스타벅스.png").getImage();
        tempImg = tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageList.add(new ImageIcon(tempImg));

        tempImg = new ImageIcon("src/images/코카콜라.png").getImage();
        tempImg = tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageList.add(new ImageIcon(tempImg));

        tempImg = new ImageIcon("src/images/박카스.png").getImage();
        tempImg = tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageList.add(new ImageIcon(tempImg));
        return imageList;
    }
}
