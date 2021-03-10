package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePWD {
    private JTextField textField1;
    private JPasswordField OldPwdField;
    private JPasswordField NewPwdField;
    private JButton backButton;
    private JButton changePasswordButton;
    private JPanel ChangePWD;
    static JFrame frame = new JFrame("Change Passwords");

    public ChangePWD() {
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPWD = OldPwdField.getText();
                String USR = IDVerif.GetUsername(textField1.getText());
                String currPWD = IDVerif.GetPassword(textField1.getText());
                if (!currPWD.equals(currentPWD) || USR == "e") {
                    ErrorPopup.main("Username or Password not recognised");
                }
                else{
                    PWDChanger.changer(USR, NewPwdField.getText());
                    try {
                        Login.main(null);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    frame.dispose();
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
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
    }
    public static class PWDChanger {
        public static void changer(String USR, String NewPWD) {
            try {
                String sql = "UPDATE User SET Password = ? WHERE USERNAME = ?";  // sql statement to add new product line

                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, NewPWD);
                pstmt.setString(2, USR);
                pstmt.executeUpdate();
                conn.close();
            } catch (SQLException e){

            }

        }
    }
    public static void main(String[] args) {
        frame.setContentPane(new ChangePWD().ChangePWD);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
