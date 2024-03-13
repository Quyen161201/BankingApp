package com.jmc.bankapp.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_HaNoiPay","root","");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    client setion
    public ResultSet getClientData(String username, String password)
    {
        Statement statement;
        ResultSet rs = null;

        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("select * from client where phone = '" + username + "' and password = '" + password + "'");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

//    adminSection
    public ResultSet getAdminData(String username, String password)
    {
        Statement statement;
        ResultSet rs = null;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("select * from admin where name = '" + username + "' and password = '" + password + "'");

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rs;

    }

}
