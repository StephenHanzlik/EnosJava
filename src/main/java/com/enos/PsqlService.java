package com.enos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.lang.ClassNotFoundException;


public class PsqlService {
//    public static void main(String[] args) {
    public String connect() throws ClassNotFoundException{
        String url = "jdbc:postgresql://localhost:5432/enos";
        String user = "enos";
        String password = "enos";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from stations;");

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

//package com.enos;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import java.lang.ClassNotFoundException;
//
///**
// *
// * @author postgresqltutorial.com
// */
//public class PsqlService {
//
//    private final String url = "jdbc:postgresql://localhost:5432/enos";
//    private final String user = "enos";
//    private final String password = "enos";
//
//    /**
//     * Connect to the PostgreSQL database
//     *
//     * @return a Connection object
//     */
//    public Connection connect() throws ClassNotFoundException {
//        Connection conn = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected to the PostgreSQL server successfully.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return conn;
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws ClassNotFoundException {
//        PsqlService psqlService = new PsqlService();
//        psqlService.connect();
//    }
//}
