package com.jmc.bankapp.Models;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account{
    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String account_number, double balance, int transactionLimit) {
        super(owner, account_number, balance);
        this.transactionLimit = new SimpleIntegerProperty(this,"Transaction Limit",transactionLimit);
    }

    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }
}