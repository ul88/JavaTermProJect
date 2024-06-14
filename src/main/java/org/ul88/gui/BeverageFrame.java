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
    //음료 선택 프레임
    public BeverageFrame(JPanel jPanel, BeverageList beverageList, MoneyList moneyList,
                         UserObject userObject, JLabel nowMoneyLabel,
                         ArrayList<JButton> beverageButtonList) {

        //음료의 이미지아이콘을 저장할 리스트
        ArrayList<ImageIcon> imageList = init(beverageList);

        int startX = 167, startY = 188;
        int x = startX, y = startY;
        // 반복문을 이용해 일정한 간격으로 버튼을 삽입
        for(int i=0;i<beverageList.getList().size();i++){
            String now = beverageList.getList().get(i).getName();
            beverageButtonList.add(new JButton());
            beverageButtonList.get(i).setBounds(x, y,30,20);
            //음료의 재고가 없으면 회색으로, 재고가 있으면 빨간색으로 버튼을 설정
            if(beverageList.getList().get(i).getRemaining() == 0){
                beverageButtonList.get(i).setBackground(Color.GRAY);
            }
            else beverageButtonList.get(i).setBackground(Color.RED);

            //버튼을 누르면 해당 음료를 선택하도록 설정
            ActionListener actionListener = (ActionEvent e) ->{
                //음료가 등록되지 않은 칸을 선택했을 때
                if(now.equals("empty")){
                    JOptionPane.showMessageDialog(null,"상품이 등록되지 않은 칸입니다.\n" +
                            "다른 상품을 선택해주세요.");
                }else{ //음료가 등록된 칸을 선택했을 때
                    userObject.setBeverage(now);
                    System.out.println(now);

                    System.out.println("구매 시작");

                    //음료를 구매하는 메소드 호출
                    ErrorCode errorCode = userObject.buyDrink(beverageList,moneyList);

                    if(errorCode == ErrorCode.SUCCESS){
                        JOptionPane.showMessageDialog(null, now+" 구매에 성공했습니다.");

                        for(int j=0;j<beverageList.getList().size();j++){
                            //구매할 음료의 가격이 투입한 가격보다 크면 빨간색으로 버튼을 바꿈 
                            if(userObject.getMoney() < beverageList.getList().get(j).getPrice()){
                                beverageButtonList.get(j).setBackground(Color.RED);
                            }
                            
                            //구매한 음료의 재고가 없으면 회색으로, 재고가 있으면 빨간색으로 버튼을 바꿈
                            if(beverageList.getList().get(j).getRemaining() == 0
                                    && beverageList.getList().get(j).getName().equals(now)) {
                                beverageButtonList.get(j).setBackground(Color.GRAY);
                            }
                        }
                    }else if(errorCode == ErrorCode.FAIL_NO_MONEY){ //돈이 부족할 때
                        JOptionPane.showMessageDialog(null, "구매에 실패하였습니다.\n" +
                                "금액을 더 많이 투입해주세요.");
                    }else if(errorCode == ErrorCode.FAIL_SOLD_OUT){ //재고가 없을 때
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
        // 반복문을 이용하여 해당 이미지에 마우스를 가져다대면 툴팁이 보이도록 만듦
        for(int i=0;i<imageList.size();i++){
            JLabel label = new JLabel(imageList.get(i));
            //음료가 등록되지 않은 칸일 때 툴팁을 설정
            if(beverageList.getList().get(i).getName().equals("empty")){
                label.setToolTipText("상품이 등록되지 않은 칸입니다.");
            }else{ //음료가 등록된 칸일 때 툴팁을 설정
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

    // 이미지 초기화 메소드
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
