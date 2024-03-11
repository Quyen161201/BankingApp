package com.jmc.bankapp.Views;

import com.jmc.bankapp.Controllers.Client.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private final StringProperty clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionView;

    private AnchorPane accountView;



    public  ViewFactory (){
        this.clientSelectedMenuItem = new SimpleStringProperty("");
    }
    public StringProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if(dashboardView == null)
        {
            try {
                dashboardView = new FXMLLoader((getClass().getResource("/Fxml/Client/Dashboard.fxml"))).load();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return dashboardView;
    }

    public AnchorPane getTransactionView() {
        if(transactionView == null){
            try {
                transactionView = new FXMLLoader((getClass().getResource("/Fxml/Client/Transactions.fxml"))).load();

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionView;
    }

    public AnchorPane getAccountView() {
        if(accountView == null){
            try {
                accountView = new FXMLLoader((getClass().getResource("/Fxml/Client/Account.fxml"))).load();

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        return accountView;

    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(loader.load());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hà Nội Bank");
        stage.show();

    }
    public  void showClientWindow()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        Scene scene = null;
        try
        {
            scene = new Scene(loader.load());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hà Nội Bank");
        stage.show();
    }
    public void closeStage(Stage stage){
            stage.close();

    }
}
