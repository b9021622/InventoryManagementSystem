package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllStockTableGUI  {


    public static void main(int BackClass) {
        JButton backbtn = new JButton("Black" );  // initialises back button component in GUI Frame
        String [][] lemons = ViewStock.view();  // takes all entries from the stock table and places them in  2D array
        DefaultTableModel tableModel = new DefaultTableModel(); // Initialises table model
        JTable table = new JTable(tableModel); // Initialises table based on model

        tableModel.addColumn("Product Code");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Product Type");   // adds columns for table
        tableModel.addColumn("Product Colour");
        tableModel.addColumn("Product Cost");
        tableModel.addColumn("Product Quantity");
        for (int i = 0; i < lemons.length-1 ; i++){
                tableModel.insertRow(i, new String[]{lemons[i][0], lemons[i][1],lemons[i][2], lemons[i][3], lemons[i][4], lemons[i][5] } );  // inserts each row of products into table

        }
        JFrame frame = new JFrame();  // initialises GUI frame
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setLocation(500, 210); // centres frame location
        frame.setVisible(true); // duh
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER); // adds scrolling table
        contentPane.add(backbtn, BorderLayout.SOUTH); // adds back btn



        frame.setSize(1000,420);
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   // takes back button to main WMPortal
                switch (BackClass){
                    case 0 :
                        WMPortal.main(null);
                        frame.dispose();
                        break;
                    case 1 :
                        PMPortal.main(null);
                        frame.dispose();
                        break;
                    case 2 :
                        SBPortal.main(null);
                        frame.dispose();
                        break;

                }
               /// Runnable[] methods = {BackWMV::main,BackPMV::main, BackSBV::main};
                ///methods[BackClass].run();
                ///frame.dispose();
            }
        });
    }
    public static class ViewStock {

        public static String[][] view() {
            String[][] data = new String[100][6];  // Initialises 2D array to read Database.
            Connection conn = com.company.Connect.connect();
            String getCount = "Select * FROM ProductsInStock";
            ResultSet rst = null;
            int count = 0;

            try {
                PreparedStatement ps = conn.prepareStatement(getCount);
                rst = ps.executeQuery();
                int i = 0;
                while (rst.next()) {
                    String prodCode = rst.getString(1);
                    String prodName = rst.getString(2);
                    String prodType = rst.getString(3);
                    String prodColour = rst.getString(4);
                    Float prodCost =  rst.getFloat(5);
                    int prodCount = rst.getInt(6);
                    String[] rec = new String[100];//a local variable to collect each row of records
                    rec[0] = prodCode;
                    rec[1]= prodName;
                    rec[2]= prodType;
                    rec[3]= prodColour;   // records product information into array row
                    rec[4]= String.valueOf(prodCost);
                    rec[5]= String.valueOf(prodCount);

                    data[i] = rec; // adds array row to the Ith index of the array
                    i++;

                }




            } catch (
                    SQLException e) {
                System.out.println(e.getMessage());
            }
            return data;
        }
    }
}


