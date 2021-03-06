package com.enos;

import com.enos.model.Station;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.lang.ClassNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;


public class PsqlService {

    private Connection connect() throws ClassNotFoundException {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            return connection;
        }catch (SQLException ex) {

            Logger lgr = Logger.getLogger(PsqlService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        //TODO: Exception handling
        return connect();

    }

    public String executeQuery(String query) throws ClassNotFoundException {

        try {
            Connection connection = connect();

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            List list = resultSetToArrayList(rs);
            String jsonString = arrayListToJSON(list);

            return jsonString;

        }catch (SQLException ex) {

            Logger lgr = Logger.getLogger(PsqlService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        //TODO: Exception Handling
        return "no data found";
    }

    public int executeUpdate(String query) throws ClassNotFoundException {

        try {
            Connection connection = connect();

            Statement st = connection.createStatement();

            return st.executeUpdate(query);

        }catch (SQLException ex) {

            Logger lgr = Logger.getLogger(PsqlService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        //TODO: Exception Handling
        return 0;
    }

    public List resultSetToArrayList(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (rs.next()){
            HashMap row = new HashMap(columns);
            for(int i=1; i<=columns; ++i){
                row.put(md.getColumnName(i),rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

    public String arrayListToJSON(List myList) {
        Gson gson = new Gson();
        String jsonArray = gson.toJson(myList);
        return jsonArray;
    }

}