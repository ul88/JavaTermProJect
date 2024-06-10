package org.ul88.gui;

import org.ul88.error.ErrorCode;
import org.ul88.object.BeverageList;
import org.ul88.object.BeverageObject;
import org.ul88.object.MoneyList;
import org.ul88.object.UserObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BeverageFrame extends JFrame{
    public BeverageFrame(JPanel jPanel, BeverageList beverageList, MoneyList moneyList,
                         UserObject userObject, JLabel nowMoneyLabel,
                         ArrayList<JButton> beverageButtonList) {

        ArrayList<ImageIcon> imageList = init(beverageList);

        int startX = 167, startY = 188;
        int x = startX, y = startY;
        for(int i=0;i<beverageList.getList().size();i++){
            String now = beverageList.getList().get(i).getName();
            beverageButtonList.add(new JButton());
            beverageButtonList.get(i).setBounds(x, y,30,20);
            if(beverageList.getList().get(i).getRemaining() == 0){
                beverageButtonList.get(i).setBackground(Color.GRAY);
            }
            else beverageButtonList.get(i).setBackground(Color.RED);
            ActionListener actionListener = (ActionEvent e) ->{
                if(now.equals("empty")){
                    JOptionPane.showMessageDialog(null,"상품이 등록되지 않은 칸입니다.\n" +
                            "다른 상품을 선택해주세요.");
                }else{
                    userObject.setBeverage(now);
                    System.out.println(now);

                    System.out.println("구매 시작");
                    ErrorCode errorCode = userObject.buyDrink(beverageList,moneyList);

                    if(errorCode == ErrorCode.SUCCESS){
                        JOptionPane.showMessageDialog(null, now+" 구매에 성공했습니다.");
                        for(int j=0;j<beverageList.getList().size();j++){
                            if(beverageList.getList().get(j).getRemaining() == 0
                                    && beverageList.getList().get(j).getName().equals(now)) {
                                beverageButtonList.get(j).setBackground(Color.GRAY);
                            }
                        }
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
            beverageButtonList.get(i).addActionListener(actionListener);
            x+=47;
            if(i%6 == 5){
                x=startX;
                y+=84;
            }
            jPanel.add(beverageButtonList.get(i));
        }

        startX = 160;
        startY = 133;
        x = startX;
        y = startY;
        ToolTipManager m = ToolTipManager.sharedInstance();
        m.setInitialDelay(0);
        m.setDismissDelay(1000);
        for(int i=0;i<imageList.size();i++){
            JLabel label = new JLabel(imageList.get(i));
            if(beverageList.getList().get(i).getName().equals("empty")){
                label.setToolTipText("상품이 등록되지 않은 칸입니다.");
            }else{
                label.setToolTipText(beverageList.getList().get(i).getName()+" "+
                        beverageList.getList().get(i).getPrice()+"원");
            }
            label.setBounds(x,y,50,50);
            x+=46;
            if(i%6 == 5){
                x=startX;
                y+=84;
            }
            jPanel.add(label);
        }
    }

    public ArrayList<ImageIcon> init(BeverageList beverageList){
        ArrayList<ImageIcon> imageList = new ArrayList<>();
        //음료의 이름과 이미지의 이름을 맞춰주세요.
        for(int i=0;i<beverageList.getList().size();i++){
            int width = 60, height = 50;
            BeverageObject beverageObject = beverageList.getList().get(i);
            Image img = new ImageIcon("src/images/"+beverageObject.getName()+".png").getImage();
            img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageList.add(new ImageIcon(img));
        }

        return imageList;
    }
}
