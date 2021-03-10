package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdjustStock {

    public static int getStock(String ProdCode){
            Connection conn = Connect.connect();
            String getCount = "Select StockCount FROM ProductsInStock WHERE ProductCode = ?";
            ResultSet rst = null;
            int count = 0;
            try {
                PreparedStatement ps = conn.prepareStatement(getCount);     // this try block grabs the current stock count for the specified product code
                ps.setString(1, ProdCode);
                rst = ps.executeQuery();
                while (rst.next()) {
                    count = rst.getInt(1);

                }
                }

            catch (
                        SQLException e) {
                    System.out.println(e.getMessage());
                }
            return count;
        }
        public static void Adjust(String ProdCode,int StockAdjust) {
            Connection conn = Connect.connect();

                int StockPorM = (AdjustStock.getStock(ProdCode) + StockAdjust);  // this line calculates the new stock count after the adjustment
                String sql = "UPDATE ProductsInStock SET StockCount = (?) WHERE ProductCode = (?) ;";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, StockPorM);
                    pstmt.setString(2, ProdCode);           // this try block updates the stock count on the DB to the new figure


                    pstmt.executeUpdate();
                    conn.close();

                } catch (
                        SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

