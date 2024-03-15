package com.jmc.bankapp.Controllers.Client;

import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Views.ClientMenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        logout_btn.setOnAction(event ->logout());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOption.DASHBOARD);

    }

    private void onTransactions(){

        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOption.TRANSACTION);
    }



    private  void onAccounts(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOption.ACCOUNTS);

    }

    public void logout()
    {
        Stage stage = (Stage) logout_btn.getScene().getWindow(); // Lấy stage của nút logout
        stage.close();
        Model.getInstance().setClientSuccessLoginFlag(false);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
