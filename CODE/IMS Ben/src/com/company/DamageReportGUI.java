package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DamageReportGUI {

    private JTextField PCfield;
    private JTextField PQTYfield;
    private JButton submitButton;
    private JButton backButton;
    private JTextField CauseOfDamageField;
    private JPanel DamageReportGUI;
    static JFrame frame = new JFrame("Damage Report");

    public DamageReportGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int countCheck = AdjustStock.getStock(PCfield.getText());
                if (countCheck == 0){
                    System.out.println("Fail,product qty is 0 or product doesn't exist");
                }
                else {
                    RecordDamaged.record(PCfield.getText(),
                            CauseOfDamageField.getText(), Integer.parseInt(PQTYfield.getText()));
                    WMPortal.main(null);
                    frame.dispose();
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
        frame.setContentPane(new DamageReportGUI().DamageReportGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static class RecordDamaged {
        public static void record(String ProdCode, String CauseOfDamage, int DamagedQty) {
            Connection conn = Connect.connect();
            int count = com.company.AdjustStock.getStock(ProdCode);  // Checks there is enough of a product to record it as damaged
            if ((count - DamagedQty) < 0) {
                System.out.println("damage report not possible, will take stock level below 0");
            } else {
                try {

                    ArrayList<Object> DamagedItem = ProdSearch.search(ProdCode); // Get deets of product to be recorded as damaged
                    com.company.AdjustStock.Adjust(ProdCode, -DamagedQty); // remove damaged stock from main inventory table
                    String sql = "INSERT INTO DamagedProducts(ProductCode, ProductName, ProductType, ProductColour, ProductCost, CauseOfDamage, StockCount) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, ProdCode);
                    pstmt.setString(2, (String) DamagedItem.get(1));
                    pstmt.setString(3, (String) DamagedItem.get(2));
                    pstmt.setString(4, (String) DamagedItem.get(3));
                    pstmt.setFloat(5, (Float) DamagedItem.get(4));
                    pstmt.setString(6, CauseOfDamage);
                    pstmt.setInt(7, DamagedQty);


                    pstmt.executeUpdate();
                    conn.close();

                }
                catch(IndexOutOfBoundsException indexOutOfBoundsException){
                    System.out.println("Incorrect product code");
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }}}
}
