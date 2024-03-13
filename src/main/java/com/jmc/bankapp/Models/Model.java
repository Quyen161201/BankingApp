package com.jmc.bankapp.Models;

import com.jmc.bankapp.Views.AccountType;
import com.jmc.bankapp.Views.ViewFactory;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    public static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private Client client;
    private boolean clientSuccessLoginFlag;

    private boolean adminSuccessLoginFlag;

    private AccountType loginAccountType = AccountType.CLIENT;



    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.client = new Client("","",null,"","",null,null);
        this.clientSuccessLoginFlag = false;
        this.adminSuccessLoginFlag = false;
    }
    public  static synchronized  Model getInstance()
    {
        if(model == null)
        {
            model = new Model();
        }
        return model;
    }



    public ViewFactory getViewFactory() {
        return viewFactory;
    }
    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public void setLoadAccountType(AccountType accountType)
    {
        this.loginAccountType = accountType;
    }
    public AccountType getLoadAccountType() {
        return loginAccountType;
    }
    public boolean getClientSuccessLoginFlag() {
        return clientSuccessLoginFlag;
    }

    public void setClientSuccessLoginFlag(boolean flag)
    {
        this.clientSuccessLoginFlag = flag;
    }

    public boolean getAdminSuccessLoginFlag() {
        return adminSuccessLoginFlag;
    }
    public void setAdminSuccessLoginFlag(boolean flag)
    {
        this.adminSuccessLoginFlag = flag;
    }

    public Client getClient() {
        return client;
    }

    public void dataClient(String username, String password)
    {
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;

        ResultSet rs = databaseDriver.getClientData(username, password);
       try {
           if(rs.isBeforeFirst()){
               while (rs.next()) {
                   this.client.nameProperty().set(rs.getString("name"));
                   this.client.phoneProperty().set(rs.getString("phone"));
                   Date sqlDate = rs.getDate("birth_date");
                   LocalDate localDate = sqlDate.toLocalDate();
                   this.client.birth_dateProperty().set(localDate);
                   this.client.cccdProperty().set(rs.getString("cccd"));
                   this.client.passwordProperty().set(rs.getString("password"));
                   this.clientSuccessLoginFlag = true;
               }

           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }

//    admin data
    public void dataAdmin(String username, String password)
    {
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;
        ResultSet rs= databaseDriver.getAdminData(username,password);

        try {
           if (rs.next())
           {
               this.adminSuccessLoginFlag = true;
           }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static   void main(String[] args) {
        Model.getInstance().dataAdmin("admin","123123");

    }

}
