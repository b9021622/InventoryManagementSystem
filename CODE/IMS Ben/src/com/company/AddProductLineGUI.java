package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProductLineGUI {
    private JPanel AddProductLineGUI;
    private JTextField PCfield;
    private JTextField PQTYfield;
    private JTextField PNfield;
    private JTextField PCostField;
    private JTextField PTfield;
    private JTextField PcolField;
    private JButton submitButton;
    private JButton backButton;
    static JFrame frame = new JFrame();

    public AddProductLineGUI() {
        submitButton.addActionListener(new ActionListener() {  // submit button, when clicked, runs AddProductLine fuinction with the Information in the PCfield and all other textfields
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AddProductLine.Add(PCfield.getText(), PNfield.getText(), PTfield.getText(), PcolField.getText(), Float.parseFloat(PCostField.getText()), Integer.parseInt(PQTYfield.getText()));  // submit button
                    WMPortal.main(null);
                    frame.dispose(); // closes current frame
                } catch (Exception d){
                    ErrorPopup.main("Error Creating Product");  // calls error popup
                    ErrorPopup.main("Error Creating Product");  // calls error popup
                }
            }
        });
        backButton.addActionListener(new ActionListener() {  // back button returns user to WMPortal.Main
            @Override
            public void actionPerformed(ActionEvent e) {
                WMPortal.main(null);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new AddProductLineGUI().AddProductLineGUI); // imports formatting from AddProductLineGUI.form
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets default action when close button pressed
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null); // sets page to middle of screenl
        frame.setVisible(true); // makes jFrame visible

    }
    public static class  AddProductLine {
        public static void Add(String ProdCode, String ProdName, String ProdType, String ProdColour, Float ProdCost, int StockCount ) {  // user inputs



            String sql = "INSERT INTO ProductsInStock(ProductCode, ProductName, ProductType, ProductColour, ProductCost, StockCount) VALUES (?,?,?,?,?,?)";  // sql statement to add new product line

            try {
                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, ProdCode);
                pstmt.setString(2, ProdName);           // Sets user inputs to the correct parameters in the prepared statement
                pstmt.setString(3, ProdType);
                pstmt.setString(4, ProdColour);
                pstmt.setFloat(5, ProdCost);
                pstmt.setInt(6, StockCount);


                pstmt.executeUpdate();
                conn.close();

            } catch (
                    SQLException e) {
                System.out.println(e.getMessage());  // catches any SQL errors
            }
        }
}}
