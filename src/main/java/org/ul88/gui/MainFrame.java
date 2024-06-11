package org.ul88.gui;

import org.ul88.object.BeverageList;
import org.ul88.object.MoneyList;
import org.ul88.object.MoneyObject;
import org.ul88.object.UserObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    private UserObject userObject = new UserObject("", 0);
    private JLabel nowMoneyLabel;
    private ArrayList<JButton> beverageButtonList = new ArrayList<>();

    public MainFrame() {

        super("자판기 프로그램");
        BeverageList beverageList = new BeverageList();
        MoneyList moneyList = new MoneyList();

        setSize(600,800);
        setLocation(500, 100);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        getContentPane().setBackground(new Color(0,0,0,0));
        Image img = new ImageIcon("src/images/자판기2.jpg").getImage();
        img = img.getScaledInstance(600,600, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(img);
        JLabel imgLabel = new JLabel(background);
        imgLabel.setBounds(0,70,600,600);

        nowMoneyLabel = new JLabel(userObject.getMoney() + "원");
        nowMoneyLabel.setBounds(327, 384, 60, 34);
        nowMoneyLabel.setBackground(Color.BLACK);
        nowMoneyLabel.setOpaque(true);
        nowMoneyLabel.setForeground(Color.RED);
        nowMoneyLabel.setFont(nowMoneyLabel.getFont().deriveFont(14.0f));

        new BeverageFrame(jPanel,beverageList, moneyList,userObject,nowMoneyLabel,beverageButtonList);
        new MoneyFrame(jPanel,beverageList,moneyList,userObject,nowMoneyLabel,beverageButtonList);
        makeReturnButton(jPanel,beverageList,moneyList);
        makeAdminButton(jPanel);

        jPanel.add(imgLabel);
        add(jPanel);

        setVisible(true);
    }

    public void makeReturnButton(JPanel jPanel,BeverageList beverageList,MoneyList moneyList){
        JButton returnMoneyButton = new JButton("반환");
        returnMoneyButton.setFont(new Font("고딕",Font.PLAIN,10));
        returnMoneyButton.setBounds(407,440,60,30);

        ActionListener actionListener = (ActionEvent e) ->{
            if(e.getActionCommand().equals("반환")){
                ArrayList<MoneyObject> returnMoneyList = userObject.returnMoney(moneyList);
                if(returnMoneyList.isEmpty()) return;
                String message = "";
                for(int i=0;i<returnMoneyList.size();i++){
                    message += Integer.toString(returnMoneyList.get(i).getAmount())+"원을 ";
                    message += Integer.toString(returnMoneyList.get(i).getRemaining());
                    message += "개 반환했습니다.\n";
                }
                JOptionPane.showMessageDialog(null,message);
                nowMoneyLabel.setText(userObject.getMoney()+"원");

                for(int i=0;i<beverageButtonList.size();i++){
                    if(beverageList.getList().get(i).getRemaining() == 0){
                        beverageButtonList.get(i).setBackground(Color.GRAY);
                    }
                    else beverageButtonList.get(i).setBackground(Color.RED);
                }
            }
        };

        returnMoneyButton.addActionListener(actionListener);
        jPanel.add(returnMoneyButton);
    }

    public void makeAdminButton(JPanel jPanel){
        JButton adminButton = new JButton("관리자 모드");
        adminButton.setBounds(480,20,100,30);

        ActionListener actionListener = (ActionEvent e) ->{
            if(e.getActionCommand().equals("관리자 모드")){
                dispose();
                new LoginFrame();
            }
        };

        adminButton.addActionListener(actionListener);
        jPanel.add(adminButton);
    }
}