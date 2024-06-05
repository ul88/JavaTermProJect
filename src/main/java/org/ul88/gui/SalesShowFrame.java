package org.ul88.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SalesShowFrame extends JFrame {
    public SalesShowFrame(){
        super("매출 확인");

        setSize(600,600);
        setLocation(300,100);
        setVisible(true);



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new AdminFrame();
            }
        });
    }
}
