package org.ul88.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    public AdminFrame(){
        super("관리자 모드");

        setSize(300,270);
        setLocation(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        ActionListener actionListener = (ActionEvent e) ->{
            if(e.getActionCommand().equals("매출 확인")){
                dispose();
                new SalesShowFrame();
            }
            else if(e.getActionCommand().equals("화폐 현황")){
                dispose();
                new MoneyCountCheckFrame();
            }
            else if(e.getActionCommand().equals("음료 변경")){
                dispose();
            }
            else if(e.getActionCommand().equals("수금")){
                dispose();
            }
            else if(e.getActionCommand().equals("비밀번호 변경")){
                dispose();
                new ChangePasswordFrame();
            }
        };

        JButton salesButton = new JButton("매출 확인");
        salesButton.setPreferredSize(new Dimension(200,40));
        salesButton.addActionListener(actionListener);
        jPanel.add(salesButton);

        JButton moneyCountCheckButton = new JButton("화폐 현황");
        moneyCountCheckButton.setPreferredSize(new Dimension(200,40));
        moneyCountCheckButton.addActionListener(actionListener);
        jPanel.add(moneyCountCheckButton);

        JButton beverageChangeButton = new JButton("음료 변경");
        beverageChangeButton.setPreferredSize(new Dimension(200,40));
        beverageChangeButton.addActionListener(actionListener);
        jPanel.add(beverageChangeButton);

        JButton collectButton = new JButton("수금");
        collectButton.setPreferredSize(new Dimension(200,40));
        collectButton.addActionListener(actionListener);
        jPanel.add(collectButton);

        JButton changePwButton = new JButton("비밀번호 변경");
        changePwButton.setPreferredSize(new Dimension(200,40));
        changePwButton.addActionListener(actionListener);
        jPanel.add(changePwButton);


        add(jPanel);

        setVisible(true);
    }
}
