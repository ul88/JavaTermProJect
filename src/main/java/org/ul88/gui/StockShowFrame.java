package org.ul88.gui;

import org.ul88.object.StockObject;

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

public class StockShowFrame extends JFrame{
    public StockShowFrame(){
        super("매출 확인");

        setSize(600,600);
        setLocation(300,100);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        ArrayList<StockObject> stockList = new ArrayList<>();

        int minYear = 9999, maxYear = 0;
        Vector<String> yearList = new Vector<>();
        String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] days = {"모든 날","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        try {
            BufferedReader file = new BufferedReader(new FileReader(
                    new File("src/main/resources/TextFile","stockData.txt")
            ));

            String str="";
            while((str = file.readLine()) != null){
                String[] stockStr = str.split(" ");

                stockList.add(new StockObject(
                        stockStr[0],
                        stockStr[1],
                        stockStr[2],
                        stockStr[3],
                        stockStr[4]
                ));
                minYear = Math.min(minYear,Integer.parseInt(stockList.getLast().year()));
                maxYear = Math.max(maxYear,Integer.parseInt(stockList.getLast().year()));
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
        model.addColumn("재고 여부");

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jPanel.add(jScrollPane);

        searchButton.addActionListener(e -> {
            String year = (String) yearBox.getSelectedItem();
            String month = (String) monthBox.getSelectedItem();
            String day = (String) dayBox.getSelectedItem();

            if(day.equals("모든 날")){
                model.setRowCount(0);

                for(StockObject stockObject : stockList) {
                    if (year.equals(stockObject.year()) && (month.equals(stockObject.month()))) {
                        model.addRow(new Object[]{
                                stockObject.getDate(),
                                stockObject.getTime(),
                                stockObject.getAM_PM(),
                                stockObject.getBeverage(),
                                stockObject.getStock()
                        });
                    }
                }
            }else{
                model.setRowCount(0);

                for(StockObject stockObject : stockList){
                    if(year.equals(stockObject.year()) && month.equals(stockObject.month()) && day.equals(stockObject.day())){
                        model.addRow(new Object[]{
                                stockObject.getDate(),
                                stockObject.getTime(),
                                stockObject.getAM_PM(),
                                stockObject.getBeverage(),
                                stockObject.getStock()
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
