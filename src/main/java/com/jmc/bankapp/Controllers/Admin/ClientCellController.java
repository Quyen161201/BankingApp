package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Controllers.Client.ClientController;
import com.jmc.bankapp.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fld_name;
    public Label lbl_ch_acc;
    public Label lbl_sav_acc;
    public Label lbl_date_create;
    public Button btn_delete;

    public Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fld_name.setText(client.nameProperty().get());
        lbl_ch_acc.setText(String.valueOf(client.CheckingAccountProperty().get().account_numberProperty().getValue()));
        lbl_sav_acc.setText(String.valueOf(client.SavingAccountProperty().get().account_numberProperty().getValue()));
        lbl_date_create.setText(String.valueOf(client.phoneProperty().get()));

    }

    public ClientCellController(Client client)
    {
        this.client = client;
    }


}
