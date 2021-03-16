package com.ss.weekone.wknd;

import com.ss.weekone.thurs.Singleton;

import java.math.BigDecimal;
import java.sql.*;

//Removed invalid static modifier
public class SampleSingleton {

    //Made instance variable volatile
    private static Connection conn = null;
    private static volatile SampleSingleton instance = null;

    public static SampleSingleton getInstance() {
        if (instance == null) {
            synchronized (SampleSingleton.class) {
                if (instance == null) {
                    instance = new SampleSingleton();
                }
            }
        }
        return instance;
    }
    //Added an SQLException and error handling
    public static void databaseQuery(BigDecimal input) {
        try {
            conn = DriverManager.getConnection("url of database");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id from table");
            //Changed x from int to big decimal
            BigDecimal x = new BigDecimal(0);
            while(rs.next()) {
                int i = rs.getInt(1);
                x = (new BigDecimal(i)).multiply(input);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
