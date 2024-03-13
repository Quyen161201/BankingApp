package com.jmc.bankapp.Models;

import java.sql.*;

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

    public int createClient(String name,String phone,String password,Date birth_date,String cccd,String account_number_ch,String account_number_sav,int withdrawal_limit,double sav_balance,double ch_balance,int transaction_limit){
        Statement statement;
        int rs1=0;
        int rs2=0;
        int rs3=0;
        PreparedStatement ps = null;
        PreparedStatement ps2 =null;
        PreparedStatement ps3 =null;

        try {

            String sql ="insert into client(name, phone, password,birth_date,cccd) values (?,?,?,?,?)";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,phone);
            ps.setString(3,password);
            ps.setDate(4,birth_date);
            ps.setString(5,cccd);
            rs1= ps.executeUpdate();

            if(rs1>0) {

                String sql2 = "insert into saving_account(owner, account_number,withdrawal_limit,balance) values (?,?,?,?)";
                ps2 = this.conn.prepareStatement(sql2);
                ps2.setString(1, name);
                ps2.setString(2, account_number_sav);
                ps2.setInt(3, withdrawal_limit);
                ps2.setDouble(4, sav_balance);
                rs2=ps2.executeUpdate();

                String sql3 = "insert into checking_account(owner, account_number,transaction_limit,balance) values (?,?,?,?)";
                ps3 = this.conn.prepareStatement(sql3);
                ps3.setString(1, name);
                ps3.setString(2, account_number_ch);
                ps3.setInt(3, transaction_limit);
                ps3.setDouble(4, ch_balance);
                rs3=ps3.executeUpdate();
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rs1+rs2+rs3;
    }

}
