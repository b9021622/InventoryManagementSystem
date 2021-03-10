package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
class BackWM{
    public static void main() {
        WMPortal.main(null);
    }
}
class BackPM{
    public static void main() {
        PMPortal.main(null);
    }

    }
class BackSB{
    public static void main() {
        SBPortal.main(null);
    }
}
public class ShowSearchResults {
    static JFrame frame = new JFrame();
    static JButton backbtn = new JButton("Back" );

    public static void main(String SearchQuery, int BackClass) {

        ArrayList<Object> searchResult = ProdSearch.search(SearchQuery);
        DefaultTableModel tableModel = new DefaultTableModel();

        JTable table = new JTable(tableModel);

        tableModel.addColumn("Search Results");
        tableModel.addColumn("Product Information");
        ;

        tableModel.insertRow(0,new Object[] {"Product Code", searchResult.get(0)});
        tableModel.insertRow(1,new Object[] {"Product Name",searchResult.get(1)});
        tableModel.insertRow(2,new Object[] {"Product Type",searchResult.get(2)});
        tableModel.insertRow(3,new Object[] {"Product Colour",searchResult.get(3)});
        tableModel.insertRow(4,new Object[] {"Product Cost (£)",searchResult.get(4)});
        tableModel.insertRow(5,new Object[] {"Product Quantity",searchResult.get(5)});



        JFrame frame = new JFrame();
        frame.setDefaultLookAndFeelDecorated(true);

        frame.setLocation(500,210);
        frame.setVisible(true);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPane.add(backbtn, BorderLayout.SOUTH);



        frame.setSize(1000,420);
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable[] methods = {BackWM::main,BackPM::main, BackSB::main};
                methods[BackClass].run();
                frame.dispose();
            }
        });
    }

}


