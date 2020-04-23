package com.enos;

import com.enos.model.Station;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.lang.ClassNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSetMetaData;

import com.google.gson.*;


public class PsqlService {
//    should split up connect and retrieve stations
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
                List list = resultSetToArrayList(rs);
                String jsonString = arrayListToJSON(list);

                return jsonString;
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(PsqlService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        //we really want to throw and exception
        return "no station found exception";
    }

//    }
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

    public String arrayListToJSON(List mylist) {
        Gson gson = new Gson();
        String jsonArray = gson.toJson(mylist);
        return jsonArray;
    }

}