module com.jmc.bankapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires mysql.connector.java;



    opens com.jmc.bankapp to javafx.fxml;
    exports com.jmc.bankapp;
    exports com.jmc.bankapp.Controllers;
    exports com.jmc.bankapp.Controllers.Client;
    exports com.jmc.bankapp.Controllers.Admin;
    exports com.jmc.bankapp.Models;
    exports com.jmc.bankapp.Views;
}