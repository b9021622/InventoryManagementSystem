package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditProductDeetsGUI {
    private JPanel EditProductDeetsGUI;
    private JTextField PCfield;
    private JTextField PQTYfield;
    private JTextField PNfield;
    private JTextField PCostField;
    private JTextField PTfield;
    private JTextField PcolField;
    private JButton submitButton;
    private JButton backButton;
    static JFrame frame = new JFrame("Edit Product Details");

    public EditProductDeetsGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                update(PCfield.getText(), PNfield.getText(), PTfield.getText(), PcolField.getText(), Float.parseFloat(PCostField.getText()), Integer.parseInt(PQTYfield.getText()));
                WMPortal.main(null);
                frame.dispose();
                }catch(Exception D){
                    ErrorPopup.main("Database Error, Please check Spelling and Try again");
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WMPortal.main(null);
                frame.dispose();
            }
        });
    }
    public static void update(String ProdCode, String ProdName, String ProdType, String ProdColour, Float ProdCost, int StockCount ){
        String sql = "UPDATE ProductsInStock SET ProductName = ?, ProductType= ?, ProductColour= ?, ProductCost= ?, StockCount = ? WHERE ProductCode = (?) ;";  // Updates product info based on product code

        try {
            Connection conn = Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, ProdName);
            pstmt.setString(2,ProdType);
            pstmt.setString(3, ProdColour);
            pstmt.setFloat(4,ProdCost);
            pstmt.setInt(5, StockCount);
            pstmt.setString(6,ProdCode);


            pstmt.executeUpdate();
            conn.close();


        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        frame.setContentPane(new EditProductDeetsGUI().EditProductDeetsGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
