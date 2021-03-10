package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdSearch {
    public static ArrayList<Object>  search(String prodCodeSearch) {
        Connection conn = Connect.connect();
        String getCount = "Select * FROM ProductsInStock WHERE ProductCode = ?";  // Search select statement
        ResultSet rst = null;


        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

        try {
            PreparedStatement ps = conn.prepareStatement(getCount);
            ps.setString(1, prodCodeSearch);
            rst = ps.executeQuery();
            while (rst.next()) {
                String prodCode = rst.getString(1);
                String prodName = rst.getString(2);    // puts search result into String
                String prodType = rst.getString(3);
                String prodColour = rst.getString(4);
                Float prodCost = rst.getFloat(5);
                int prodCount = rst.getInt(6);
                ArrayList<Object> rec = new ArrayList<Object>();//a local variable to collect each row of records
                rec.add(prodCode);
                rec.add(prodName);
                rec.add(prodType);
                rec.add(prodColour);
                rec.add(prodCost);
                rec.add(prodCount);

                data.add(rec);

            }


        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("[ProductCode, ProductName, ProductType, ProductColour, ProductCost, StockCount]");
        if (data.size() >= 0) {
            return data.get(0);// gives the array of search results to the caller
        }
        else{
            return null;
        }

    }
    }
