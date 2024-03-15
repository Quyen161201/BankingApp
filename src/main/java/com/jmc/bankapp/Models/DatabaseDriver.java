package com.jmc.bankapp.Models;

import java.sql.*;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_HaNoiPay", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    client setion
    public ResultSet getClientData(String username, String password) {
        Statement statement;
        PreparedStatement ps=null;
        ResultSet rs = null;
        String sql= "SELECT * FROM client c, checking_account ca, saving_account sa WHERE c.phone = ca.phone AND c.phone = sa.phone AND c.phone = ? AND c.password = ?";
        try {

            ps = this.conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int createTransaction(String sender , String receiver, double amount,String message)
    {
        int rs = 0;
        PreparedStatement ps = null;
        try {
            String sql = "insert into transaction(sender,receiver,amount,message) values (?,?,?,?)";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setDouble(3, amount);
            ps.setString(4, message);
            rs = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean isPhoneClient(String phone) {
        ResultSet rs = null;
        PreparedStatement statement = null;
        boolean exists = false;

        try {
            String query = "SELECT * FROM client WHERE phone = ?";
            statement = this.conn.prepareStatement(query);
            statement.setString(1, phone);
            rs = statement.executeQuery();

            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    public ResultSet transactionList(String sender)
    {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "Select * from transaction where sender = ? or receiver = ?";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, sender);
            ps.setString(2, sender);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet savingAccountAndCheckingAccount(String phone)
    {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "Select * from checking_account ca,saving_account sa where ca.phone = sa.phone and ca.phone =?";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    //    adminSection
    public ResultSet getAdminData(String username, String password) {
        Statement statement;
        ResultSet rs = null;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("select * from admin where name = '" + username + "' and password = '" + password + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

        }

        return rs;

    }

    public int createClient(String name, String phone, String password, Date birth_date, String cccd, String account_number_ch, String account_number_sav, int withdrawal_limit, double sav_balance, double ch_balance, int transaction_limit) {
        Statement statement;
        int rs1 = 0;
        int rs2 = 0;
        int rs3 = 0;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        try {

            String sql = "insert into client(name, phone, password,birth_date,cccd) values (?,?,?,?,?)";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, password);
            ps.setDate(4, birth_date);
            ps.setString(5, cccd);
            rs1 = ps.executeUpdate();

            if (rs1 > 0) {

                String sql2 = "insert into saving_account(owner,phone ,account_number,withdrawal_limit,balance) values (?,?,?,?,?)";
                ps2 = this.conn.prepareStatement(sql2);

                ps2.setString(1, name);
                ps2.setString(2,phone);
                ps2.setString(3, account_number_sav);
                ps2.setInt(4, withdrawal_limit);
                ps2.setDouble(5, sav_balance);
                rs2 = ps2.executeUpdate();

                String sql3 = "insert into checking_account(owner,phone ,account_number,transaction_limit,balance) values (?,?,?,?,?)";
                ps3 = this.conn.prepareStatement(sql3);
                ps3.setString(1, name);
                ps3.setString(2, phone);
                ps3.setString(3, account_number_ch);
                ps3.setInt(4, transaction_limit);
                ps3.setDouble(5, ch_balance);
                rs3 = ps3.executeUpdate();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs1 + rs2 + rs3;
    }


    public ResultSet getClients() {
        Statement statement;
        ResultSet rs = null;

        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("select * from client");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getCheckingAccount() {
        Statement statement;
        ResultSet rs = null;

        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("select * from checking_account");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getSavingAccount() {
        Statement statement;
        ResultSet rs = null;

        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("select * from saving_account");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int updateCheckingAccountBalance(String phone, double balance)
    {
        String sql = "UPDATE db_hanoipay.checking_account SET balance =balance - ? WHERE phone =?";
        String sql2 = "UPDATE db_hanoipay.saving_account SET balance =balance + ? WHERE phone =?";
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        int rs = 0;
        int rs2=0;
        try {
            ps = this.conn.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setString(2, phone);
            rs = ps.executeUpdate();
            if (rs > 0) {
                ps2 = this.conn.prepareStatement(sql2);
                ps2.setDouble(1,balance);
                ps2.setString(2, phone);
                rs2 = ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs+rs2;
    }

    public int updateSavingAccountBalance(String phone, double balance)
    {
        String sql = "UPDATE db_hanoipay.saving_account SET balance =balance - ? WHERE phone =?";
        String sql2 = "UPDATE db_hanoipay.checking_account SET balance =balance + ? WHERE phone =?";
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        int rs = 0;
        int rs2=0;
        try {
            ps = this.conn.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setString(2, phone);
            rs = ps.executeUpdate();
            if (rs > 0) {
                ps2 = this.conn.prepareStatement(sql2);
                ps2.setDouble(1,balance);
                ps2.setString(2, phone);
                rs2 = ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs+rs2;
    }

    public int deleteClient(String name) {
        String sql = "DELETE FROM client WHERE name = ?";
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchClient(String phone) {
        Statement statement;
        ResultSet rs = null;

        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("select * from client c , checking_account ca ,saving_account sa where c.name = ca.owner and c.name = sa.owner and c.phone ='" + phone + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int setAmountDeposit(double balance, String phone) {
        String sql = "UPDATE db_hanoipay.checking_account SET balance = balance + ? WHERE phone = ?;";

        PreparedStatement ps = null;

        int rs = 0;
        try {
            ps = this.conn.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setString(2, phone);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
