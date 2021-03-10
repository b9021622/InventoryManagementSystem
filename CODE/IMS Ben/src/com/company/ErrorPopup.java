package com.company;

import com.company.WMPortal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorPopup {
    private JPanel panel1;
    private JTextArea TextArea;
    private JButton backButton;



    public static void main(String errorStr) {  //
        JFrame frame = new JFrame("Error"); // creates new jframe
        frame.setLocation(720,320);  // centers jframe
        frame.setSize(400,420); // sets jframe size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true); // makes it look faaaancy
        JLabel errCode = new JLabel(String.valueOf(errorStr)); //
        errCode.setBounds(50, 0, 300, 30);
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(100, 50, 200, 30);
        frame.add(errCode);
        frame.add(backBtn);
        frame.setLayout(null);
        frame.setVisible(true);


    backBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }  // closes error page upon pressing on back button
    });

}
}
