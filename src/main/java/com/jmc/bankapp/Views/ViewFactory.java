package com.jmc.bankapp.Views;

import com.jmc.bankapp.Controllers.Admin.AdminController;
import com.jmc.bankapp.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private AccountType loginAccountType;
    private final ObjectProperty<ClientMenuOption> clientSelectedMenuItem;

    private final ObjectProperty<AdminMenuOption> AdminSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionView;

    private AnchorPane accountView;
    private AnchorPane createClientView;

    private AnchorPane clientsListView;

    private  AnchorPane DepositView;



    public  ViewFactory (){
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.AdminSelectedMenuItem = new SimpleObjectProperty<>();
    }
    public ObjectProperty<ClientMenuOption> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
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
        CreateStage(loader);

    }


    public  void showClientWindow()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));

        ClientController clientController = new ClientController();
        loader.setController(clientController);
        CreateStage(loader);
    }



//    selection admin
    public ObjectProperty<AdminMenuOption> getAdminSelectedMenuItem(){
        return AdminSelectedMenuItem;
    }
    public void showAdminWindow(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        CreateStage(loader);
    }
    public AnchorPane getCreateClientView() {
        if(transactionView == null){
            try {
                transactionView = new FXMLLoader((getClass().getResource("/Fxml/Admin/CreateClient.fxml"))).load();

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionView;
    }

    public AnchorPane getClientsListView() {

            try {
                clientsListView = new FXMLLoader((getClass().getResource("/Fxml/Admin/Clients.fxml"))).load();

            }
            catch (Exception e){
                e.printStackTrace();
            }

        return clientsListView;
    }

    public AnchorPane getDepositView(){
        if(DepositView == null){
            try {
                DepositView = new FXMLLoader((getClass().getResource("/Fxml/Admin/Deposit.fxml"))).load();

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return DepositView;

    }

    private void CreateStage(FXMLLoader loader){
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
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/hnpay-04.png"))));
        stage.setResizable(false);

        stage.show();

    }
    public void closeStage(Stage stage){
            stage.close();

    }


}
