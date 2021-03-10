package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IDVerif {

    public static String GetUsername(String UsrNm){
        String UN = "";

        try {
            Connection conn = com.company.Connect.connect();
            String getU = "Select Username FROM User WHERE Username = ?";
            PreparedStatement ps = conn.prepareStatement(getU);
            ps.setString(1,  UsrNm );
            ResultSet UNS = ps.executeQuery();

            while (UNS.next()){
                UN = UNS.getString(1);

            }
            conn.close();
            return UN;

        } catch(SQLException d){
            d.printStackTrace();
        }

    return "E";
    }


    public static String GetPassword(String UsrNm){
        String PD = "";
        try {
            Connection conn = com.company.Connect.connect();
            String getU = "Select Password FROM User WHERE Username = ?";
            PreparedStatement ps = conn.prepareStatement(getU);
            ps.setString(1, String.valueOf(UsrNm));
            ResultSet PWS = ps.executeQuery();

            while (PWS.next()){
                PD = PWS.getString(1);

            }
            conn.close();
            return PD;



        } catch(Exception d){}

    return "E";

    }
    public static String GetUSR(String UsrNm){
        String UT = "";
        try {
            Connection conn = com.company.Connect.connect();
            String getU = "Select UserType FROM User WHERE Username = ?";
            PreparedStatement ps = conn.prepareStatement(getU);
            ps.setString(1, UsrNm);
            ResultSet URS = ps.executeQuery();

            while (URS.next()){
                UT = URS.getString(1);

            }
            conn.close();
            return UT;



        } catch(Exception d){}

        return "E";
    }

}
