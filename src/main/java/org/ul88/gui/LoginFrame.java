package org.ul88.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame {
    public LoginFrame(){
        super("비밀번호 입력창");
        setSize(500,100);
        setLocation(500, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel pwLabel = new JLabel("  비밀번호");
        pwLabel.setBounds(0,20,50,30);
        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(70,20,300,30);
        JButton loginButton = new JButton("로그인");
        loginButton.setBounds(380,20,100,30);

        ActionListener actionListener = (ActionEvent e)->{
            if(e.getActionCommand().equals("로그인")){
                String successPassWord = "";
                try {
                    BufferedReader file = new BufferedReader(new FileReader(
                            new File("src/main/resources/TextFile","password.txt")
                    ));
                    successPassWord = file.readLine();
                    file.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                String password = new String(pwField.getPassword());
                if(password.equals(successPassWord)){
                    JOptionPane.showMessageDialog(null,"로그인에 성공했습니다.");
                    dispose();
                    new AdminFrame();
                }else{
                    JOptionPane.showMessageDialog(null,"비밀번호가 틀렸습니다.\n" +
                            "다시 입력해주세요.");
                }
            }
        };

        loginButton.addActionListener(actionListener);
        add(pwField);
        add(loginButton);
        add(pwLabel);

        setVisible(true);
    }
}
