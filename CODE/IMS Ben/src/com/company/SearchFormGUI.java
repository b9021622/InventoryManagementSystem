package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
class BackWMS{
    public static void main() {         // for all these classes, BackWMS and similar are a way to avoid
                                        // reuse of code by passing the return location to the correct portal when
                                        // the back button is pressed.
        WMPortal.main(null);
    }
}
class BackPMS{
    public static void main() {
        PMPortal.main(null);
    }

}
class BackSBS{
    public static void main() {
        SBPortal.main(null);
    }
}
public class SearchFormGUI {

    private JTextField ProductCode;
    private JPanel SearchFormGUI;
    private JButton backButton;
    private JButton submitButton;
    static JFrame frame = new JFrame("Search");
    static ArrayList<Object> searchResult = new ArrayList<>();

    public SearchFormGUI(int BackClass) {  // backclass is an int that brings the user back to the right portal
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable[] methods = {BackWMS::main,BackPMS::main, BackSBS::main};
                methods[BackClass].run();    // runs the correct back function
                frame.dispose();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ShowSearchResults.main(ProductCode.getText(), BackClass);
                    frame.dispose();
                } catch(Exception d){
                    ErrorPopup.main("Product Doesn't exist");
                }

            }
        });
    }
    public static void main(int BackClass) {
        DefaultTableModel tableModel = new DefaultTableModel();
        frame.setContentPane(new SearchFormGUI(BackClass).SearchFormGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        }
    }

