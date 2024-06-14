package org.ul88.gui;

import org.ul88.object.RevenueObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class SalesShowFrame extends JFrame {
    public SalesShowFrame(){
        super("매출 확인");

        setSize(600,600);
        setLocation(300,100);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        ArrayList<RevenueObject> revenueList = new ArrayList<>();

        // 최소, 최대 연도를 구하기 위한 변수
        int minYear = 9999, maxYear = 0;
        Vector<String> yearList = new Vector<>();
        String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] days = {"모든 날","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        // revenueData.txt 파일을 읽어 revenueList에 저장
        try {
            BufferedReader file = new BufferedReader(new FileReader(
                    new File("src/main/resources/TextFile","revenueData.txt")
            ));

            String str="";
            while((str = file.readLine()) != null){
                String[] revenueStr = str.split(" ");

                revenueList.add(new RevenueObject(
                        revenueStr[0],
                        revenueStr[1],
                        revenueStr[2],
                        revenueStr[3],
                        Integer.parseInt(revenueStr[4])
                ));
                minYear = Math.min(minYear,Integer.parseInt(revenueList.getLast().year()));
                maxYear = Math.max(maxYear,Integer.parseInt(revenueList.getLast().year()));
            }

            for(int i=minYear;i<=maxYear;i++){
                yearList.add(Integer.toString(i));
            }
            file.close();
        } catch (IOException ex) {
            System.out.println("파일을 불러올 수 없습니다.");
            throw new RuntimeException(ex);
        }

        JComboBox<String> yearBox = new JComboBox<>(yearList);
        JComboBox<String> monthBox = new JComboBox<>(months);
        JComboBox<String> dayBox = new JComboBox<>(days);

        jPanel.add(yearBox);
        jPanel.add(monthBox);
        jPanel.add(dayBox);

        JButton searchButton = new JButton("검색");
        jPanel.add(searchButton);

        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        jTable.setModel(model);
        model.addColumn("날짜");
        model.addColumn("시간");
        model.addColumn("오전/오후");
        model.addColumn("음료");
        model.addColumn("가격");

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jPanel.add(jScrollPane);

        // 검색 버튼을 누르면 해당하는 날짜의 매출을 테이블에 출력
        searchButton.addActionListener(e -> {
            String year = (String) yearBox.getSelectedItem();
            String month = (String) monthBox.getSelectedItem();
            String day = (String) dayBox.getSelectedItem();

            if(day.equals("모든 날")){
                model.setRowCount(0);

                for(RevenueObject revenueObject : revenueList) {
                    if (year.equals(revenueObject.year()) && (month.equals(revenueObject.month()))) {
                        model.addRow(new Object[]{
                                revenueObject.getDate(),
                                revenueObject.getTime(),
                                revenueObject.getAM_PM(),
                                revenueObject.getBeverage(),
                                revenueObject.getPrice()
                        });
                    }
                }
            }else{
                model.setRowCount(0);

                for(RevenueObject revenueObject : revenueList){
                    if(year.equals(revenueObject.year()) && month.equals(revenueObject.month()) && day.equals(revenueObject.day())){
                        model.addRow(new Object[]{
                                revenueObject.getDate(),
                                revenueObject.getTime(),
                                revenueObject.getAM_PM(),
                                revenueObject.getBeverage(),
                                revenueObject.getPrice()
                        });
                    }
                }
            }
        });

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
