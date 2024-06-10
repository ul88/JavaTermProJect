package org.ul88.gui;

import org.ul88.object.BeverageList;
import org.ul88.object.StockObject;
import org.ul88.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeverageManagementFrame extends JFrame {
    public BeverageManagementFrame(){
        super("음료 관리");
        setSize(460,660);
        setLocation(300,100);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        BeverageList beverageList = new BeverageList();

        JLabel titleIdxLabel = new JLabel("슬롯 번호");
        titleIdxLabel.setPreferredSize(new Dimension(60,40));
        jPanel.add(titleIdxLabel);

        JLabel titleNameLabel = new JLabel("음료 이름");
        titleNameLabel.setPreferredSize(new Dimension(100,40));
        jPanel.add(titleNameLabel);

        JLabel titlePriceLabel = new JLabel("음료 가격");
        titlePriceLabel.setPreferredSize(new Dimension(100,40));
        jPanel.add(titlePriceLabel);

        JLabel titleRemainingLabel = new JLabel("음료 재고");
        titleRemainingLabel.setPreferredSize(new Dimension(160,40));
        jPanel.add(titleRemainingLabel);

        for(int i=0;i<beverageList.getList().size();i++){
            JLabel idxLabel = new JLabel(i+1+"번");
            idxLabel.setPreferredSize(new Dimension(60,40));
            jPanel.add(idxLabel);
            JTextField nameField = new JTextField(beverageList.getList().get(i).getName());
            nameField.setPreferredSize(new Dimension(100,40));
            jPanel.add(nameField);
            JTextField priceField = new JTextField(beverageList.getList().get(i).getPrice()+"");
            priceField.setPreferredSize(new Dimension(100,40));
            jPanel.add(priceField);
            JTextField remainingField = new JTextField(beverageList.getList().get(i).getRemaining()+"");
            remainingField.setPreferredSize(new Dimension(100,40));
            jPanel.add(remainingField);
            JButton changeButton = new JButton("변경");
            changeButton.setPreferredSize(new Dimension(60,40));

            changeButton.addActionListener(e -> {
                try{
                    if(nameField.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"이름 입력이 안되어 빈 칸으로 변경됩니다.");
                        beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).setName("empty");
                        beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).setPrice(0);
                        beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).setRemaining(0);
                    }
                    else if(priceField.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"가격 입력이 안되어 변경되지 않습니다.");
                    }
                    else if(remainingField.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"재고 입력이 안되어 변경되지 않습니다.");
                    }
                    else{
                        if(nameField.getText().contains(" ")) {
                            JOptionPane.showMessageDialog(null, "이름에 공백이 있어 변경되지 않습니다.");
                        }else{
                            if(Integer.parseInt(priceField.getText()) < 0 || Integer.parseInt(remainingField.getText()) < 0 ){
                                JOptionPane.showMessageDialog(null,"가격 또는 재고에 음수가 입력되어 변경되지 않습니다.");
                            }
                            else{
                                beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).setName(nameField.getText());
                                beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).setPrice(Integer.parseInt(priceField.getText()));
                                if(beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).getRemaining() != Integer.parseInt(remainingField.getText())){
                                    int temp = beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).getRemaining() - Integer.parseInt(remainingField.getText());
                                    StockObject stockObject;
                                    if(temp < 0) {
                                        Date nowDate = new Date();
                                        stockObject = new StockObject(
                                                new SimpleDateFormat("yyyy-MM-dd").format(nowDate),
                                                new SimpleDateFormat("HH:mm:ss").format(nowDate),
                                                new SimpleDateFormat("a").format(nowDate),
                                                beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).getName(),
                                                Integer.toString(Math.abs(temp))+"개 추가"
                                        );
                                    }else{
                                        Date nowDate = new Date();
                                        stockObject = new StockObject(
                                                new SimpleDateFormat("yyyy-MM-dd").format(nowDate),
                                                new SimpleDateFormat("HH:mm:ss").format(nowDate),
                                                new SimpleDateFormat("a").format(nowDate),
                                                beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).getName(),
                                                Integer.toString(Math.abs(temp))+"개 감소"
                                        );
                                    }
                                    new Repository(stockObject);
                                }
                                beverageList.getList().get(Integer.parseInt(idxLabel.getText().substring(0, 1)) - 1).setRemaining(Integer.parseInt(remainingField.getText()));
                                JOptionPane.showMessageDialog(null, "변경되었습니다.");
                            }
                        }
                    }
                    new Repository(beverageList);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"가격 또는 재고에 숫자가 아닌 값을 입력했습니다.");
                }
            });
            jPanel.add(changeButton);
        }

        add(jPanel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new AdminFrame();
            }
        });
    }
}
