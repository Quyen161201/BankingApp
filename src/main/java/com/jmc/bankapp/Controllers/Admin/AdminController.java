package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener(((observableValue,olVal,newVal) -> {
            switch (newVal){
                case CREATE_CLIENT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
                case CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsListView());
                case DEPOSIT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());

            }
        }));
    }
}
