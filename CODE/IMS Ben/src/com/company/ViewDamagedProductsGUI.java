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

class BackWMD{
    public static void main() {         // for all these classes, BackWMS and similar are a way to avoid
        // reuse of code by passing the return location to the correct portal when
        // the back button is pressed.
        WMPortal.main(null);
    }
}
class BackPMD{
    public static void main() {
        PMPortal.main(null);
    }

}
class BackSBD{
    public static void main() {
        SBPortal.main(null);
    }
}
public class ViewDamagedProductsGUI {
    static JFrame frame = new JFrame();  // Initialises gui frame
    static JButton backbtn = new JButton("Back" ); // Back button initialisation

    public static void main(int BackClass) {

        String [][] lemons = ViewDamagedProducts.view();  // gets damaged products and places into 2Darray Lemons
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel); // same table initialisation as AllStockTableGUI
        tableModel.addColumn("Damage Report NO.");
        tableModel.addColumn("Product Code");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Product Type");
        tableModel.addColumn("Product Colour");
        tableModel.addColumn("Product Cost");
        tableModel.addColumn("Cause Of Damage");
        tableModel.addColumn("Product Quantity");
        for (int i = 0; i < lemons.length-1 ; i++){
            tableModel.insertRow(i, new String[]{lemons[i][0], lemons[i][1],lemons[i][2], lemons[i][3], lemons[i][4], lemons[i][5], lemons[i][6], lemons[i][7]} );  // inserts row from array into table

        }
        JFrame frame = new JFrame();
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setLocation(500, 210);
        frame.setVisible(true);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPane.add(backbtn, BorderLayout.SOUTH);



        frame.setSize(1000,420);
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable[] methods = {BackWMD::main,BackPMD::main, BackSBD::main};
                methods[BackClass].run();    // runs the correct back function
                frame.dispose();
            }
        });
    }
    public static class ViewDamagedProducts {

        public static String[][] view() {
            String[][] data = new String[100][8];  // 2d array to store damaged product table
            Connection conn = com.company.Connect.connect();
            String getCount = "Select * FROM DamagedProducts";  // view all damaged products
            ResultSet rst = null;
            int count = 0;

            try {
                PreparedStatement ps = conn.prepareStatement(getCount);
                rst = ps.executeQuery();
                int i = 0;
                while (rst.next()) {
                    int DamRepCode = rst.getInt(1);
                    String prodCode = rst.getString(2);
                    String prodName = rst.getString(3);
                    String prodType = rst.getString(4);
                    String prodColour = rst.getString(5);
                    Float prodCost =  rst.getFloat(6);
                    String DamageCause = rst.getString(7);
                    int prodCount = rst.getInt(8);
                    String[] rec = new String[100];//a local variable to collect each row of records
                    rec[0] = String.valueOf(DamRepCode);
                    rec[1] = prodCode;
                    rec[2]= prodName;
                    rec[3]= prodType;
                    rec[4]= prodColour;
                    rec[5]= String.valueOf(prodCost);
                    rec[6] = DamageCause;
                    rec[7]= String.valueOf(prodCount);

                    data[i] = rec;
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