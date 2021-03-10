package com.company;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignOutProductGUI extends JFrame {
    private JTextField ProductQTY;
    private JPanel SignOutProductGUI;
    private JTextField ProductCode;
    private JButton submitButton;
    private JButton backButton;
    static JFrame frame = new JFrame("Sign Out Product");

    public SignOutProductGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int stockMinus = (0 - Integer.parseInt(ProductQTY.getText()));
                    int checkStock = AdjustStock.getStock(ProductCode.getText()); // checks if there is enough stock to sign out
                    if ((checkStock + stockMinus) < 0) {
                        ErrorPopup.main("Product doesn't exist or is out of stock");

                    } else {
                        AdjustStock.Adjust(ProductCode.getText(), stockMinus); // adjusts stock
                        WMPortal.main(null);  // return to main portal
                        frame.dispose();
                    }
                } catch (Exception d){
                    ErrorPopup.main("Product doesn't exist or is out of stock");
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WMPortal.main(null);  // goes back to WMPortal
                frame.dispose();

            }
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new com.company.SignOutProductGUI().SignOutProductGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}