package com.jmc.bankapp.Models;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account{
    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String account_number, double balance,String phone, int transactionLimit) {
        super(owner, account_number, balance,phone);
        this.transactionLimit = new SimpleIntegerProperty(this,"Transaction Limit",transactionLimit);
    }

    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }
}
