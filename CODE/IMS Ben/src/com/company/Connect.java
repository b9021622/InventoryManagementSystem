package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    static Connection connect() {
        Connection conn = null;
        try {

            //try to make connection to the db file
            String fileName = "C:\\sqlite\\db\\TEST.db";
            String url = "jdbc:sqlite:" + fileName;

// create a connection to the database
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
