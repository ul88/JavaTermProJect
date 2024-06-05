package org.ul88.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class ChangePasswordFrame extends JFrame {
    public ChangePasswordFrame(){
        super("비밀번호 변경창");
        setSize(500,200);
        setLocation(500, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        JLabel pwLabel = new JLabel("  비밀번호");
        pwLabel.setBounds(0,20,100,30);
        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(120,20,300,30);
        JLabel oneMoreLabel = new JLabel("비밀번호 확인");
        oneMoreLabel.setBounds(0,50,100,30);
        JPasswordField oneMorePwField = new JPasswordField();
        oneMorePwField.setBounds(120,50,300,30);
        JLabel changePwLabel = new JLabel("변경할 비밀번호");
        changePwLabel.setBounds(0,80,100,30);
        JPasswordField changePwField = new JPasswordField();
        changePwField.setBounds(120,80,300,30);
        JButton loginButton = new JButton("변경");
        loginButton.setBounds(0,110,100,30);

        ActionListener actionListener = (ActionEvent e) -> {
            if(e.getActionCommand().equals("변경")){
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

                String reg = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
                String password1 = new String(pwField.getPassword());
                String password2 = new String(oneMorePwField.getPassword());
                String changePassword = new String(changePwField.getPassword());
                if(password1.equals(password2)){
                    if(!password1.equals(successPassWord)){
                        JOptionPane.showMessageDialog(null,"현재 비밀번호와 다릅니다.\n" +
                                "다시 입력해주세요.");
                    }
                    else if(changePassword.equals(successPassWord)){
                        JOptionPane.showMessageDialog(null,"원래 비밀번호와 같습니다.\n" +
                                "새로운 비밀번호로 다시 입력해주세요.");
                    }
                    else if(changePassword.matches(reg)){
                        try {
                            BufferedWriter file = new BufferedWriter(new FileWriter(
                                    new File("src/main/resources/TextFile","password.txt")
                            ));
                            file.write(changePassword);
                            file.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        JOptionPane.showMessageDialog(null,"변경에 성공했습니다.");
                        dispose();
                        new AdminFrame();
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "비밀번호는 특수문자 및 숫자가 각각 하나 이상 포함된 8자리 이상이어야 합니다.\n" +
                                        "다시 입력해주세요.");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "비밀번호와 비밀번호 확인이 서로 다릅니다.\n" +
                                    "다시 입력해주세요.");
                }
            }
        };

        loginButton.addActionListener(actionListener);
        add(loginButton);
        add(pwLabel);
        add(pwField);
        add(oneMoreLabel);
        add(oneMorePwField);
        add(changePwLabel);
        add(changePwField);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new AdminFrame();
            }
        });
    }
}
