package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Login extends JFrame {
    private JPanel Login;
    private JButton loginButton;
    private JPasswordField passwordField1;
    private JTextField usernameTextField;
    static JFrame frame = new JFrame("Login");

    public Login() {


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x = passwordField1.getText();
                String USR = IDVerif.GetUsername(usernameTextField.getText());
                String pass = IDVerif.GetPassword(usernameTextField.getText());
                String UsrType = IDVerif.GetUSR(usernameTextField.getText());

                if (!pass.equals(x) || USR == "e") {
                    ErrorPopup.main("Username or Password not recognised");
                }
                else {
                    switch(UsrType){
                        case "WM":
                            WMPortal.main(null);
                            frame.dispose();
                            break;
                        case "PM":
                            PMPortal.main(null);
                            frame.dispose();
                            break;
                        case "SB":
                            SBPortal.main(null);
                            frame.dispose();
                            break;
                    }


                }


            }
        });
    }

    public static void main(String[] args) throws SQLException {
        frame.setContentPane(new Login().Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
