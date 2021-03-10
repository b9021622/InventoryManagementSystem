package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WMPortal extends JFrame {
    private JPanel WMPortal;
    private JButton editProductDetailsButton;
    private JButton recordDeliveryButton;
    private JButton signOutStockButton;
    private JButton createProductButton;
    private JButton searchForAProductButton;
    private JButton viewAllStockButton;
    private JButton discontinueProductButton;
    private JButton recordDamagedProductButton;
    private JButton viewAllDamageReportsButton;
    private JButton LogOffButton;
    private JButton changePasswordButton;
    static JFrame frame = new JFrame("Warehouse Manager Portal");
    static int BackClass = 0;  // Back class is passed through the functions so the GUI knows which portal to return to

    public WMPortal() {
        recordDeliveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecordDeliveryGUI.main(null);
                frame.dispose();

            }
        });
        signOutStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignOutProductGUI.main(null);
                frame.dispose();
            }
        });
        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductLineGUI.main(null);
                frame.dispose();
            }
        });
        discontinueProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiscontinueProductGUI.main(null);
                frame.dispose();
            }
        });
        editProductDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditProductDeetsGUI.main(null);
                frame.dispose();
            }
        });
        viewAllStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllStockTableGUI.main(BackClass);
                frame.dispose();
            }
        });
        searchForAProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchFormGUI.main(BackClass);
                frame.dispose();
            }
        });
        recordDamagedProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DamageReportGUI.main(null);
                frame.dispose();
            }
        });

        viewAllDamageReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewDamagedProductsGUI.main(BackClass);
                frame.dispose();
            }
        });
        LogOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Login.main(null);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                frame.dispose();
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePWD.main(null);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {

        frame.setContentPane(new WMPortal().WMPortal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
