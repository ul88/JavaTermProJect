package org.ul88.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminFrame extends JFrame {
    // 관리자 모드 화면
    public AdminFrame(){
        super("관리자 모드");

        setSize(300,280);
        setLocation(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        // 버튼 클릭 시 해당하는 화면으로 이동
        ActionListener actionListener = (ActionEvent e) ->{
            if(e.getActionCommand().equals("매출 확인")){
                dispose();
                new SalesShowFrame();
            }
            else if(e.getActionCommand().equals("화폐 현황")){
                dispose();
                new MoneyCountCheckFrame();
            }
            else if(e.getActionCommand().equals("음료 관리")){
                dispose();
                new BeverageManagementFrame();
            }
            else if(e.getActionCommand().equals("비밀번호 변경")){
                dispose();
                new ChangePasswordFrame();
            }
            else if(e.getActionCommand().equals("재고 없음 확인")){
                dispose();
                new StockShowFrame();
            }
        };

        // 버튼 생성 및 이벤트 추가
        JButton salesButton = new JButton("매출 확인");
        salesButton.setPreferredSize(new Dimension(200,40));
        salesButton.addActionListener(actionListener);
        jPanel.add(salesButton);

        JButton stockButton = new JButton("재고 없음 확인");
        stockButton.setPreferredSize(new Dimension(200,40));
        stockButton.addActionListener(actionListener);
        jPanel.add(stockButton);

        JButton moneyCountCheckButton = new JButton("화폐 현황");
        moneyCountCheckButton.setPreferredSize(new Dimension(200,40));
        moneyCountCheckButton.addActionListener(actionListener);
        jPanel.add(moneyCountCheckButton);

        JButton beverageChangeButton = new JButton("음료 관리");
        beverageChangeButton.setPreferredSize(new Dimension(200,40));
        beverageChangeButton.addActionListener(actionListener);
        jPanel.add(beverageChangeButton);

        JButton changePwButton = new JButton("비밀번호 변경");
        changePwButton.setPreferredSize(new Dimension(200,40));
        changePwButton.addActionListener(actionListener);
        jPanel.add(changePwButton);

        add(jPanel);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new MainFrame();
            }
        });
    }
}
