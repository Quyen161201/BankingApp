package com.jmc.bankapp.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {
    private final StringProperty owner;
    private final StringProperty account_number;
    private final DoubleProperty balance;

    public Account(String owner, String account_number, double balance) {
        this.owner = new SimpleStringProperty(owner);
        this.account_number = new SimpleStringProperty(account_number);
        this.balance = new SimpleDoubleProperty(balance);
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public StringProperty account_numberProperty() {
        return account_number;
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }
}
