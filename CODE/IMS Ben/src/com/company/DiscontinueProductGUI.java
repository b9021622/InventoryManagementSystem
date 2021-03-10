package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscontinueProductGUI {
    private JPanel DiscontinueProductGUI;
    private JTextField PCfield;
    private JButton submitButton;
    private JButton backButton;
    static JFrame frame = new JFrame("Discontinue Product");

    public DiscontinueProductGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int countCheck = AdjustStock.getStock(PCfield.getText());
                if (countCheck == 0){
                DiscontinueProduct.discontinue(PCfield.getText());
                WMPortal.main(null);
                frame.dispose();}
                else{
                    ErrorPopup.main("Product Either Doesn't exist or can't be discontinued due to existing stock.");  // Error popup to catch if there is still stock or if the product doesnt exist
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

    public static void main(String[] args) {
        frame.setContentPane(new DiscontinueProductGUI().DiscontinueProductGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();                                                           // this block initialises the format and grabs the formatting from the .form page
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static class  DiscontinueProduct {
        public static void discontinue(String ProdCode){
            Connection conn = Connect.connect();
            ResultSet rst = null;
            int count = com.company.AdjustStock.getStock(ProdCode); // gets current stock of product to see if it can be discontinued
            if(count == 0){
                String sql = "DELETE FROM ProductsInStock WHERE ProductCode = ?";  // SQL statement for deleting a row
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql); // tries Delete statement
                    pstmt.setString(1,ProdCode);

                    pstmt.executeUpdate();
                    conn.close();

                } catch (
                        SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            else{
                System.out.println("can't discontinue product, there is still existing stock");
            }
        }
    }
}
