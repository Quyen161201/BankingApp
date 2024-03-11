package com.jmc.bankapp.Controllers.Client;

import com.jmc.bankapp.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button account_btn;
    public Button profile_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();

    }
    private void addListener() {
        dashboard_btn.setOnAction(event -> onDashboard());
        transaction_btn.setOnAction(event -> onTransactions());
        account_btn.setOnAction(event ->onAccounts());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");

    }

    private void onTransactions(){
        System.out.println("Transactions");
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Transactions");
    }

    private  void onAccounts(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Accounts");

    }
}
