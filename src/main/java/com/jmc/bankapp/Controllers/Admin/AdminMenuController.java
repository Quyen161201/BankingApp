package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Views.AdminMenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
}
