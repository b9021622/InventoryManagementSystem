package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordDeliveryGUI extends JFrame {
private JTextField ProductQTY;
private JPanel RecordDeliveryGUI;
private JTextField ProductCode;
private JButton submitButton;
    private JButton BackBtn;
    static JFrame frame = new JFrame("Portal");

    public RecordDeliveryGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdjustStock.Adjust(ProductCode.getText(), Integer.parseInt(ProductQTY.getText()));
                int checkCount = AdjustStock.getStock(ProductCode.getText());
                if (checkCount == 0){
                    System.out.println("Stock Adjustment Unsuccessful, please check spelling.");   // checks that stock has actually been added to somewhere as a way of checking that the record acctually exists,
                }
                else{WMPortal.main(null);
                    frame.dispose();}

            }
        });
        BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WMPortal.main(null);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {

        frame.setContentPane(new RecordDeliveryGUI().RecordDeliveryGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
        }
