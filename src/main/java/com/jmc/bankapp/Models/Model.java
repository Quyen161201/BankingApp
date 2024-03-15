package com.jmc.bankapp.Models;

import com.jmc.bankapp.Views.AccountType;
import com.jmc.bankapp.Views.ViewFactory;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    public static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    public   Client client;
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

    public void dataClient(String username, String password,ObservableList<Client> clientObservable)
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

                   savingAccount = new SavingAccount(
                           rs.getString("owner"),
                           rs.getString("sa.account_number"),
                           rs.getDouble("sa.balance"),
                           rs.getString("phone"),
                           rs.getDouble("withdrawal_limit")
                   );
                   client.SavingAccountProperty().set(savingAccount);

                   checkingAccount = new CheckingAccount(
                           rs.getString("owner"),
                           rs.getString("ca.account_number"),
                           rs.getDouble("ca.balance"),
                           rs.getString("phone"),
                           rs.getInt("transaction_limit")
                   );
                   client.CheckingAccountProperty().set(checkingAccount);
                   Client client = new Client(this.client.nameProperty().get(), this.client.phoneProperty().get(), this.client.birth_dateProperty().get(), this.client.cccdProperty().get(), this.client.passwordProperty().get(), savingAccount, checkingAccount);
                   clientObservable.add(client);
                   this.clientSuccessLoginFlag = true;
               }


           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    public int createTransaction(String sender,String receiver,double amount,String message)
    {
        return databaseDriver.createTransaction(sender,receiver,amount,message);

    }

    public void transactionList(String sender, ObservableList<Transaction> transactionObservableList){
        ResultSet rs = databaseDriver.transactionList(sender);
         try {
             while (rs.next())
             {
                 Transaction transaction = new Transaction(
                         rs.getString("sender"),
                         rs.getString("receiver"),
                         rs.getDouble("amount"),
                         rs.getDate("date").toLocalDate(),
                         rs.getString("message")
                 );
                 transactionObservableList.add(transaction);
             }

         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
    }

    public  void savingAccountAndCheckingAccount(String phone,ObservableList<SavingAccount>savingAccounts,ObservableList<CheckingAccount>checkingAccounts)
    {
        ResultSet rs = databaseDriver.savingAccountAndCheckingAccount(phone);
        SavingAccount savingAccount;
        CheckingAccount checkingAccount;
        try {
            while (rs.next())
            {
                savingAccount = new SavingAccount(
                        rs.getString("sa.owner"),
                        rs.getString("sa.account_number"),
                        rs.getDouble("sa.balance"),
                        rs.getString("sa.phone"),
                        rs.getDouble("sa.withdrawal_limit")
                );
                savingAccounts.add(savingAccount);
                checkingAccount = new CheckingAccount(
                        rs.getString("ca.owner"),
                        rs.getString("ca.account_number"),
                        rs.getDouble("ca.balance"),
                        rs.getString("ca.phone"),
                        rs.getInt("ca.transaction_limit")
                );
                checkingAccounts.add(checkingAccount);
                System.out.println("rs"+rs.getString("ca.account_number"));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isPhoneClient(String phone){
        boolean exist = databaseDriver.isPhoneClient(phone);
        return exist;

    };



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

    public int createDataClient(String name,String phone,String password,Date birth_date,String cccd,String account_number_ch,String account_number_sav,int withdrawal_limit,double sav_balance,double ch_balance, int transaction_limit)
    {
        return databaseDriver.createClient(name,phone,password,birth_date,cccd,account_number_ch,account_number_sav,withdrawal_limit,sav_balance,ch_balance,transaction_limit);
    }


    public  int deleteClient(String name){
        return databaseDriver.deleteClient(name);
    }

    public int updateCheckingAccountBalance(String phone, double balance)
    {
        return databaseDriver.updateCheckingAccountBalance(phone,balance);
    }

    public void listClient(ObservableList<Client> clientObservableList)
    {
        CheckingAccount checkingAccount = null;
        SavingAccount savingAccount = null;

        ResultSet rs = databaseDriver.getClients();
        ResultSet rs_saving_account= databaseDriver.getSavingAccount();
        ResultSet rs_checking_account= databaseDriver.getCheckingAccount();
        try {
                while (rs.next()) {

                    this.client.nameProperty().set(rs.getString("name"));
                    this.client.phoneProperty().set(rs.getString("phone"));
                    Date sqlDate = rs.getDate("birth_date");
                    LocalDate localDate = sqlDate.toLocalDate();
                    this.client.birth_dateProperty().set(localDate);
                    this.client.cccdProperty().set(rs.getString("cccd"));
                    this.client.passwordProperty().set(rs.getString("password"));


                    if (rs_saving_account.next()) {
                        savingAccount = new SavingAccount(
                                rs_saving_account.getString("owner"),
                                rs_saving_account.getString("account_number"),
                                rs_saving_account.getDouble("balance"),
                                rs_saving_account.getString("phone"),
                                rs_saving_account.getDouble("withdrawal_limit")
                        );
                        client.SavingAccountProperty().set(savingAccount);
                    }


                    if (rs_checking_account.next()) {
                        checkingAccount = new CheckingAccount(
                                rs_checking_account.getString("owner"),
                                rs_checking_account.getString("account_number"),
                                rs_checking_account.getDouble("balance"),
                                rs_checking_account.getString("phone"),
                                rs_checking_account.getInt("transaction_limit")
                        );
                        client.CheckingAccountProperty().set(checkingAccount);

                    }
                    Client client = new Client(this.client.nameProperty().get(), this.client.phoneProperty().get(), this.client.birth_dateProperty().get(), this.client.cccdProperty().get(), this.client.passwordProperty().get(), savingAccount, checkingAccount);
                    clientObservableList.add(client);
                }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public int updateSavingAccountBalance(String phone,double balance)
    {
        return databaseDriver.updateSavingAccountBalance(phone, balance);
    }

    public void searchCLient(String phone,ObservableList<Client> clientObservable){
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;
        ResultSet rs= databaseDriver.searchClient(phone);
        try {
            if(rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    this.client.nameProperty().set(rs.getString("name"));
                    this.client.phoneProperty().set(rs.getString("phone"));
                    Date sqlDate = rs.getDate("birth_date");
                    LocalDate localDate = sqlDate.toLocalDate();
                    this.client.birth_dateProperty().set(localDate);
                    this.client.cccdProperty().set(rs.getString("cccd"));
                    this.client.passwordProperty().set(rs.getString("password"));
                    this.clientSuccessLoginFlag = true;

                    savingAccount = new SavingAccount(
                            rs.getString("owner"),
                            rs.getString("account_number"),
                            rs.getDouble("balance"),
                            rs.getString("phone"),
                            rs.getDouble("withdrawal_limit")
                    );
                    client.SavingAccountProperty().set(savingAccount);

                    checkingAccount = new CheckingAccount(
                            rs.getString("owner"),
                            rs.getString("account_number"),
                            rs.getDouble("balance"),
                            rs.getString("phone"),
                            rs.getInt("transaction_limit")
                    );
                    client.CheckingAccountProperty().set(checkingAccount);
                    Client client = new Client(this.client.nameProperty().get(), this.client.phoneProperty().get(), this.client.birth_dateProperty().get(), this.client.cccdProperty().get(), this.client.passwordProperty().get(), savingAccount, checkingAccount);
                    clientObservable.add(client);
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int depositAccount (double balance, String phone)
    {
        return databaseDriver.setAmountDeposit(balance,phone);
    }

}
