package com.jmc.bankapp.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty name;
    private  final StringProperty phone;
    private final ObjectProperty<LocalDate> birth_date;
    private final StringProperty cccd;
    private final StringProperty password;

    private final ObjectProperty<Account> SavingAccount;
    private final ObjectProperty<Account> CheckingAccount;


    public Client(String name, String phone, LocalDate birth_date, String cccd, String password, Account SavingAccount, Account CheckingAccount) {
        this.name = new SimpleStringProperty(this,"Name",name);
        this.phone = new SimpleStringProperty(this,"Phone",phone);
        this.birth_date = new SimpleObjectProperty<>(this, "Birth Date",birth_date);
        this.cccd = new SimpleStringProperty(this,"CCCD",cccd);
        this.password = new SimpleStringProperty(this,"Password",password);
        this.SavingAccount = new SimpleObjectProperty<>(this, "Saving Account",SavingAccount);
        this.CheckingAccount = new SimpleObjectProperty<>(this, "Checking Account",CheckingAccount);


    }

    public StringProperty nameProperty() { return this.name; }
    public StringProperty phoneProperty() { return this.phone; }
    public StringProperty cccdProperty() { return this.cccd;}
    public StringProperty passwordProperty() { return this.password; }
    public ObjectProperty<Account> SavingAccountProperty() { return this.SavingAccount; }
    public ObjectProperty<Account> CheckingAccountProperty() { return this.CheckingAccount; }
    public ObjectProperty<LocalDate> birth_dateProperty() { return this.birth_date; }


}
