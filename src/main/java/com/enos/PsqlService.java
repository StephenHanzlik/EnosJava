package com.enos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PsqlService {
//    public static void main(String[] args) {
    public String connectToDb(){
        String url = "jdbc:postgresql://localhost:5432/enos";
        String user = "enos";
        String password = "enos";
        System.out.println("running main");

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(PsqlService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "a string";
    }

//    }

}
