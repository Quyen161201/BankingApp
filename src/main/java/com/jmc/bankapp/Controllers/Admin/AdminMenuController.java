package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.App;
import com.jmc.bankapp.Controllers.LoginController;
import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Views.AdminMenuOption;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class AdminMenuController implements Initializable {

    public Button admin_create_user;
    public Button admin_list_user;
    public Button admin_deposit;
    public Button admin_logout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListener();
    }

    public void addListener()
    {
        admin_create_user.setOnAction(event -> createClient());
        admin_list_user.setOnAction(event -> listClient() );
        admin_deposit.setOnAction(event -> onDeposit());
        admin_logout.setOnAction(event -> logout());

    }

    public void createClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.CREATE_CLIENT);

    }

    public void listClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.CLIENTS);
    }
     public void onDeposit()
     {
         Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.DEPOSIT);
     }

     public void logout()
     {
         Stage stage = (Stage) admin_logout.getScene().getWindow(); // Lấy stage của nút logout
         stage.close();
         Model.getInstance().setAdminSuccessLoginFlag(false);
         Model.getInstance().getViewFactory().showLoginWindow();
     }

    public static Stage getCurrentStage(Node node) {
        if (node == null) {
            return null;
        }
        Stage stage = (Stage) node.getScene().getWindow();
        return stage;
    }
}
