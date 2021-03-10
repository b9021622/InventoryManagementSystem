package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SBPortal {
    private JButton searchForAProductButton;
    private JButton viewAllStockButton;
    private JButton LogOffButton;
    private JPanel SBPortal;
    private JButton changePasswordButton;
    static int BackClass = 2; // Back class is passed through the functions so the GUI knows which portal to return to
    static JFrame frame = new JFrame("System Builder Portal");

    public SBPortal() {

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

        frame.setContentPane(new SBPortal().SBPortal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}