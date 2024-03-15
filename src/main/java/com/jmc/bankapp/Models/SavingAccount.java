package com.jmc.bankapp.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingAccount extends Account{
    private final DoubleProperty withdrawalLimit;

    public SavingAccount(String owner, String account_number, double balance,String phone ,double withdrawalLimit)
    {
        super(owner, account_number, balance, phone);
        this.withdrawalLimit = new SimpleDoubleProperty(this, "Withdrawal Limit", withdrawalLimit);
    }


    public DoubleProperty withdrawalLimitProperty() {
        return withdrawalLimit;
    }
}
